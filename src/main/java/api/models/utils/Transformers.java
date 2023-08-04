package api.models.utils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.HashMap;
import java.util.Map;

import api.models.statements.Row;

public class Transformers {

    private static String toLowerCase(String columnName) {
        return columnName.toLowerCase();
    }

    @SuppressWarnings("deprecation")
	public static <T> T instanceOf(Class<T> clazz, Row row) {
        try {
            T bean = clazz.newInstance();
            HashMap<String, Object> columns = row.getColumns();

            for (Map.Entry<String, Object> entry : columns.entrySet()) {
                String columnName = entry.getKey();
                Object columnValue = entry.getValue();

                String fieldName = toLowerCase(columnName);
                Field field = findFieldIgnoreCase(clazz, fieldName);

                if (field != null) {
                    field.setAccessible(true);

                    if (field.getType().equals(OffsetDateTime.class) && columnValue instanceof String) {
                        columnValue = parseOffsetDateTime((String) columnValue);
                    } else if (field.getType().equals(LocalDateTime.class) && columnValue instanceof String) {
                        columnValue = parseLocalDateTime((String) columnValue);
                    } else if (field.getType().equals(LocalDate.class) && columnValue instanceof String) {
                        columnValue = parseLocalDate((String) columnValue);
                    } else if (field.getType().equals(LocalTime.class) && columnValue instanceof String) {
                        columnValue = LocalTime.parse((String) columnValue);
                    } else if (field.getType().equals(OffsetTime.class) && columnValue instanceof String) {
                        columnValue = OffsetTime.parse((String) columnValue);
                    } else if ((field.getType().equals(boolean.class) || field.getType().equals(Boolean.class))
                               && columnValue instanceof Integer) {
                        int intValue = (int) columnValue;
                        columnValue = intValue == 1;
                    }

                    field.set(bean, columnValue);
                } else {
                    // Campo não encontrado, registrar um aviso ou lançar uma exceção
                    System.out.println("Warning: Field '" + columnName + "' not found in class " + clazz.getName());
                }
            }

            return bean;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    private static OffsetDateTime parseOffsetDateTime(String value) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatterWithTime2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        try {
            LocalDate localDate = LocalDate.parse(value, formatter1);
            return localDate.atStartOfDay().atOffset(ZoneOffset.UTC);
        } catch (DateTimeParseException e1) {
            try {
                LocalDate localDate = LocalDate.parse(value, formatter2);
                return localDate.atStartOfDay().atOffset(ZoneOffset.UTC);
            } catch (DateTimeParseException e2) {
                try {
                    LocalDateTime localDateTime = LocalDateTime.parse(value, formatterWithTime);
                    return localDateTime.atOffset(ZoneOffset.UTC);
                } catch (DateTimeParseException e3) {
                    LocalDateTime localDateTime = LocalDateTime.parse(value, formatterWithTime2);
                    return localDateTime.atOffset(ZoneOffset.UTC);
                }
            }
        }
    }

    private static LocalDateTime parseLocalDateTime(String value) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            if (value.length() == 19) {
                return LocalDateTime.parse(value, formatter1);
            } else if (value.length() == 16) {
                return LocalDateTime.parse(value, formatter2);
            } else if (value.length() == 10) {
                LocalDate localDate = LocalDate.parse(value, formatter3);
                return localDate.atStartOfDay();
            } else {
                throw new DateTimeParseException("Invalid date format", value, 0);
            }
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Invalid date format", value, 0);
        }
    }
    
    

    private static LocalDate parseLocalDate(String value) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            return LocalDate.parse(value, formatter1);
        } catch (DateTimeParseException e) {
            return LocalDate.parse(value, formatter2);
        }
    }

    private static Field findFieldIgnoreCase(Class<?> clazz, String fieldName) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().equalsIgnoreCase(fieldName)) {
                return field;
            }
        }
        return null;
    }
}
