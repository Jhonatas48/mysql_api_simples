package api.models.statements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import api.interfaces.createatrrubutes.IForeignKey;
import api.interfaces.createatrrubutes.IPrimaryKey;
import api.interfaces.createatrrubutes.IUnique;

public class Create {

	private String table;
	private Row row;
	private IPrimaryKey primaryKey;
	private boolean autoIncrement= false;
	private List<IForeignKey>foreignKeys = new ArrayList<>();
	private List<IUnique>unique = new ArrayList<>();
	//private List<>
	public Create() {
		row = new Row();
	}
	
	public void addColumn(String column,String properties) {
		
		row.addcolumn(column, properties);
	}
	
	public HashMap<String,Object>getColums(){
		return row.getColumns();
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public List<IForeignKey> getForeignKeys() {
		return foreignKeys;
	}

	public void setForeignKeys(List<IForeignKey> foreignKeys) {
		this.foreignKeys = foreignKeys;
	}

	public IPrimaryKey getPrimaryKey() {
		return primaryKey;
	}
	
	public void setPrimaryKey(IPrimaryKey primaryKey) {
		this.primaryKey = primaryKey;
	}

	public boolean isAutoIncrement() {
		return autoIncrement;
	}

	public void setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	public List<IUnique> getUniqueKeys() {
		return unique;
	}

	public void setUniqueKeys(List<IUnique> unique) {
		this.unique = unique;
	}
	
	

}
