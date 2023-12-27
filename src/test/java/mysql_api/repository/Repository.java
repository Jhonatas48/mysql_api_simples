package mysql_api.repository;

import java.util.List;

public interface Repository<T> {

	  boolean save();
	  boolean update();
	  boolean delete();
	  List<T> getAll();
}
