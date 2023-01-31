package Flexberry.GIS.utils;

import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;
import org.postgis.Geometry;
import org.postgis.PGgeometry;
import org.postgresql.util.PGobject;

public class PGgeometryConverter implements Converter {
    private static final long serialVersionUID = 4516918363697406455L;
    public static final String POSTGIS_GEOMETRY_DB_TYPE = "geometry";
    public static final Class<? extends PGobject> POSTGIS_GEOMETRY_CLASS = PGgeometry.class;


    @Override
    public PGgeometry convertObjectValueToDataValue(Object objectValue, Session session) {
        if ((null == objectValue) || (null == session) || (!(objectValue instanceof org.postgis.Geometry)))
            return null;
        return new org.postgis.PGgeometry((org.postgis.Geometry)objectValue);
    }


    @Override
    public Geometry convertDataValueToObjectValue(Object dataValue, Session session) {
        if ((null == dataValue) || (null == session) || (!(dataValue instanceof PGgeometry)))
            return null;
        return ((PGgeometry)dataValue).getGeometry();
    }


    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public void initialize(DatabaseMapping mapping, Session session) {
        mapping.getField().setSqlType(java.sql.Types.OTHER);
    }
}
