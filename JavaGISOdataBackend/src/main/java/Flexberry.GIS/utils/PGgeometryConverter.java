package Flexberry.GIS.utils;

import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;
import org.postgis.PGgeometry;

import java.sql.Types;

public class PGgeometryConverter implements Converter {
    private static final long serialVersionUID = 4516918363697406455L;

    @Override
    public PGgeometry convertObjectValueToDataValue(Object objectValue, Session session) {
        if ((null == objectValue) || (null == session) || (!(objectValue instanceof org.postgis.Geometry)))
            return null;

        // TODO тут нужно будет брать строку JSON и создавать из нее тип PGgeometry.
        return new org.postgis.PGgeometry((org.postgis.Geometry)objectValue);
    }


    @Override
    public String convertDataValueToObjectValue(Object dataValue, Session session) {
        if ((null == dataValue) || (null == session) || (!(dataValue instanceof PGgeometry)))
            return "";

        // TODO из этого объекта должен получаться JSON.
        return ((PGgeometry)dataValue).getGeometry().toString();
    }


    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public void initialize(DatabaseMapping mapping, Session session) {
        DatabaseField field = mapping.getField();
        field.setSqlType(java.sql.Types.OTHER);
        field.setTypeName("java.lang.String");
        field.setColumnDefinition("GEOGRAPHY");
    }
}
