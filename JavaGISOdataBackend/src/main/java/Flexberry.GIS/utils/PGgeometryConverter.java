package Flexberry.GIS.utils;

import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;
import org.json.JSONObject;
import org.postgis.Geometry;
import org.postgis.PGgeometry;
import org.postgis.Point;
import org.postgresql.util.PGobject;

import java.sql.SQLException;

public class PGgeometryConverter implements Converter {
    private static final long serialVersionUID = 4516918363697406455L;

    @Override
    public PGgeometry convertObjectValueToDataValue(Object objectValue, Session session) {
        if (objectValue == null || objectValue.toString().equals("") || null == session)
            return null;

        JSONObject geometryJson = new JSONObject((String) objectValue);

        return ConvertGISJsonToPGgeometry(geometryJson);
    }


    @Override
    public String convertDataValueToObjectValue(Object dataValue, Session session) {
        if ((null == dataValue) || (null == session) || (!(dataValue instanceof PGobject)))
            return "";

        PGobject postgresObject = (PGobject) dataValue;

        return ConvertPGobjectWithGeometryToString(postgresObject);
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public void initialize(DatabaseMapping mapping, Session session) {
        DatabaseField field = mapping.getField();
        field.setSqlType(java.sql.Types.OTHER);
        field.setTypeName("org.postgis.PGgeometry");
        field.setColumnDefinition("GEOGRAPHY");
    }

    private PGgeometry ConvertGISJsonToPGgeometry(JSONObject gisJson) {
        String srid = "SRID=4326";

        if (gisJson.has("crs")) {
            JSONObject crsFromJson =  gisJson.getJSONObject("crs");
            if (crsFromJson.has("properties")) {
                JSONObject propertiesJson = crsFromJson.getJSONObject("properties");
                if (propertiesJson.has("name")) {
                    String stringEPSGValue = propertiesJson.get("name").toString();
                    srid = stringEPSGValue.replace("EPSG:", "SRID=");
                }
            }
        }

        String geometryType = "PONT";

        if (gisJson.has("type")) {
            geometryType = gisJson.get("type").toString().toUpperCase();
        }

        String coordinates = "(0 0)";

        if (gisJson.has("coordinates")) {
            coordinates = gisJson.get("coordinates").toString();
            coordinates = coordinates.replaceAll("\\[", "(").replaceAll("\\]",")");
            coordinates = coordinates.replaceAll(",", " ").replaceAll("\\) \\(", "),(");
        }

        String geometryStringForPostgis = srid + ";" + geometryType + coordinates;

        try {
            Geometry geometryObject = PGgeometry.geomFromString(geometryStringForPostgis);
            PGgeometry postgisGeometryObject = new PGgeometry(geometryObject);

            return postgisGeometryObject;
        } catch (SQLException e) {
            return null;
        }
    }

    private String ConvertPGobjectWithGeometryToString(PGobject postgresObject) {
        try {
            Geometry geometry = PGgeometry.geomFromString(postgresObject.getValue());

            int srid = geometry.srid;
            String type = geometry.getTypeString().toLowerCase();
            type = type.substring(0, 1).toUpperCase() + type.substring(1);

            String coordinates = "";

            int pointsCount = geometry.numPoints();
            for (int i = 0; i < pointsCount; i++) {
                Point point = geometry.getPoint(i);
                coordinates += "[" + point.getX() + "," + point.getY() + "]";
            }

            coordinates = coordinates.replaceAll("\\]\\[", "],[");
            coordinates = "[[" + coordinates + "]]";

            String result = String.format(
                    "{\"crs\":{\"type\":\"name\",\"properties\":{\"name\":\"EPSG:%s\"}},\"coordinates\":%s,\"type\":\"%s\"}",
                    srid,
                    coordinates,
                    type);

            return result;
        } catch (SQLException e) {
            return "";
        }
    }
}
