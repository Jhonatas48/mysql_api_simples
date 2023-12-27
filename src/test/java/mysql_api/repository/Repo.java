package mysql_api.repository;

import java.util.Arrays;
import java.util.List;

public class Repo<T> implements Repository<T> {

	 @Override
	    public boolean save() {
	        Arrays.asList(this.getClass().getDeclaredFields()).forEach(field -> {
	            try {
	                field.setAccessible(true);
	                Object value = field.get(this);
	                System.out.println("Campo: " + field.getName() + " = " + value);
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            }
	        });
	        return false;
	    }

	@Override
	public boolean update() {
		
		return false;
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
