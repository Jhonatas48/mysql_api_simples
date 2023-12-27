package api.models.annotations.processor;

import java.util.List;
import java.util.Optional;

import api.connection.ConnectionManager;
import api.exception.TableNotExistsException;
import api.exception.annotations.AnnotationNotFoundException;
import api.interfaces.annotations.Table;
import api.models.utils.Checkers;

public class TableProcessor {

	public <T> String process(Class<?> instance,ConnectionManager manager) throws AnnotationNotFoundException {
		
		if (!instance.isAnnotationPresent(Table.class)) {
		    throw new AnnotationNotFoundException("@Table", instance.getClass().getName());
		}

		Table annotation = instance.getAnnotation(Table.class);
		
		List<String> tables = manager.getTables();
		if(Checkers.isListEmpty(tables)) {
			return null;
		}
		
		String nameClass = annotation.name();
		
		Optional<String>table =tables.stream().filter(tableInstance -> tableInstance.equalsIgnoreCase(nameClass)).findFirst();
		try {
			return  table.get();
		}catch (Exception e) {
			throw new TableNotExistsException("Table "+nameClass+" not exists");
			
		}
		
	}
}
