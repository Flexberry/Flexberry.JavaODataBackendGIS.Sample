package Flexberry.GIS.utils;

import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.sessions.Session;

import java.sql.Types;
import java.util.UUID;

public class UUIDToStringConverter implements org.eclipse.persistence.mappings.converters.Converter {
    private static final long serialVersionUID = 1L;

    @Override
    public Object convertObjectValueToDataValue(Object objectValue, Session session) {
        return objectValue == null ? null : UUID.fromString(String.valueOf(objectValue));
    }

    @Override
    public Object convertDataValueToObjectValue(Object dataValue, Session session) {
        return dataValue == null ? null : dataValue.toString();
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public void initialize(DatabaseMapping mapping, Session session) {
        DatabaseField field = mapping.getField();
        field.setSqlType(Types.OTHER);
        field.setTypeName("java.util.UUID");
        field.setColumnDefinition("UUID");
    }
}