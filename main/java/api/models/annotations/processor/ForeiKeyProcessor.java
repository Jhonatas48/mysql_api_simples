package api.models.annotations.processor;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import api.connection.ConnectionManager;
import api.exception.TableColumnNotExistsException;
import api.exception.TableNotExistsException;
import api.interfaces.annotations.ForeignKey;
import api.models.Transaction;
import api.models.statements.Result;
import api.models.statements.Row;
import api.models.statements.Select;
import api.models.utils.Checkers;

public class ForeiKeyProcessor {
//
//	public <T> List<T> process(Class<T> clazz) {
//		
//		return null;
//	}
	
	public <T> T process(T instance,ConnectionManager manager) throws NoSuchFieldException{
	
		Class<?> objectClass = instance.getClass();
		for(Field field : objectClass.getDeclaredFields()) {
			
			 if (!field.isAnnotationPresent(ForeignKey.class)) {
				 continue;
			 }
		
			
			field.setAccessible(true);
			ForeignKey foreignKeyAnnotation = field.getAnnotation(ForeignKey.class);
            Class<?> targetEntity = foreignKeyAnnotation.targetEntity();
            String referencedColumnName = foreignKeyAnnotation.referencedColumnName();
            String mappedBy = foreignKeyAnnotation.mappedByOrigin();
        	String table = new TableProcessor().process(targetEntity, manager);
            Checkers.validateStringNotNull(referencedColumnName, "referencedColumnName"); 
            List<Field>fieldsTarget =  Arrays.asList(targetEntity.getDeclaredFields());
            
            Optional<Field>optionalField = fieldsTarget.stream().filter(fieldTarget-> fieldTarget.getName().equals(referencedColumnName)).findFirst();
            Field fieldTarget = null;
            
            try {
            	
               fieldTarget = optionalField.get();
            }catch (Exception e) {
				// TODO: handle exception
            	throw new NoSuchFieldException(referencedColumnName+" not found in class "+targetEntity.getName());
			}

            
            fieldTarget.setAccessible(true);
            Object value =null;
            try {
            	Field fieldOrign = instance.getClass().getDeclaredField(mappedBy);
            	fieldOrign.setAccessible(true);
				value = fieldOrign.get(instance);
				System.out.println("COL: "+referencedColumnName+" = "+value);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          //  System.out.println("Teste: "+referencedColumnName+" = "+fieldTarget.get(instance));
           
        
          
           List<String>columnsTables =  manager.getTableFields(table);
           
           if(Checkers.isListEmpty(columnsTables)) {
        	   throw new TableNotExistsException();
           }
           
           Optional<String> columnObject = columnsTables.stream().filter(columnName -> columnName.equalsIgnoreCase(referencedColumnName)).findFirst();
           String  column = null;
           try {
        	
        	  column= columnObject.get();
           
           }catch (Exception e) {
        	   throw new TableColumnNotExistsException("Column "+referencedColumnName+" not exists");
           }
          
           List<?> result= new Transaction()
            .select()
            .setConnectionManager(manager)
            .setTable(table)
            .filter(column+" = "+value)
            .queryList(targetEntity);
            try {
            	Class<?>fieldType = field.getType();
            	
            	if (fieldType.isAssignableFrom(List.class)) {
            		 // Se for uma lista, verifica se o tipo de elemento da lista é o mesmo que o tipo da consulta
//                    Class<?> genericType = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
//                    if (!genericType.equals(fieldTarget.getType())) {
//                    	System.out.println("TIPOs orig: "+fieldTarget.getType().toString()+"-"+genericType.toString());
//                    	throw new IllegalArgumentException("Tipos incompatíveis para o campo referenciado.");
//                    }
                    
                    field.set(instance, result);
                    continue;
            	}
            	
            	
            	
            	//Se o campo não for um lista ele continua a execucao
            	
            	//Verifca se o tipo do campo e compativel
//            	if(!fieldType.equals(fieldTarget.getType())){
//            		throw new IllegalArgumentException("Tipos incompatíveis para o campo referenciado.");
//            	}
            	
            	if(!Checkers.isListEmpty(result)) {
            		field.set(instance,result.get(0));
            	}
            	
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println(fieldTarget.getType());
           
            

		}
		return instance;
	}
	
	
}
