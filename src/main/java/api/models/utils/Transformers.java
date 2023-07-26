package api.models.utils;

import java.util.List;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transformers {
	
	  @SuppressWarnings("deprecation")
	public static <T> T instanceOf(Class<T> clazz, List<String> columns, ResultSet rs) {
	        try {
	            T bean = clazz.newInstance();
	            int index = 0;
	            for (String column : columns) {
	                index++;
	                Field field = clazz.getField(column);
	                field.set(bean, rs.getObject(index, field.getType()));
	            }
	            return bean;
	            
	        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SQLException | NoSuchFieldException | SecurityException e) {
	            throw new RuntimeException(e);
	        }
	    }

}
