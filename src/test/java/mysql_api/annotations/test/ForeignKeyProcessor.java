package mysql_api.annotations.test;

import java.lang.reflect.Field;

import api.interfaces.annotations.ForeignKey;

import java.lang.reflect.Field;

import java.lang.reflect.Field;
import java.util.List;

public class ForeignKeyProcessor {
    public static void process(Object entity) throws IllegalAccessException {
        Class<?> clazz = entity.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ForeignKey.class)) {
                ForeignKey foreignKeyAnnotation = field.getAnnotation(ForeignKey.class);
                Class<?> targetEntity = foreignKeyAnnotation.targetEntity();
                String referencedColumnName = foreignKeyAnnotation.referencedColumnName();

                field.setAccessible(true);
                Object fieldValue = field.get(entity);

                if (fieldValue instanceof List<?>) {
                    // Se for uma lista, iterar sobre os elementos
                    List<?> list = (List<?>) fieldValue;
                    for (Object listItem : list) {
                        // Obter o valor do campo referenciado na classe Plans
                        Field referencedField;
                        try {
                            referencedField = targetEntity.getDeclaredField(referencedColumnName);
                            referencedField.setAccessible(true);
                            Object referencedValue = referencedField.get(listItem);

                            // Aqui você pode fazer o que quiser com o valor referenciado
                            System.out.println("Processing foreign key relationship...");
                            System.out.println("Field: " + field.getName());
                            System.out.println("Target Entity: " + targetEntity.getSimpleName());
                            System.out.println("Referenced Column: " + referencedColumnName);
                            System.out.println("Field Value: " + referencedValue);
                        } catch (NoSuchFieldException e) {
                            throw new IllegalArgumentException("Campo referenciado não encontrado na classe alvo.");
                        }
                    }
                }
            }
        }
    }
}

