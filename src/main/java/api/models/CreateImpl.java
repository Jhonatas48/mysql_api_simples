package api.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import api.connection.IConnection;
import api.exception.ColumnsIsNullException;
import api.exception.DuplicatePrimaryKeyException;
import api.exception.TypeIsNotAllowed;
import api.interfaces.ICreate;
import api.interfaces.ICreateAtributes;
import api.interfaces.IForeignKey;
import api.interfaces.IPrimaryKey;
import api.models.atributes.ForeignKey;
import api.models.enums.CreateAtributes;
import api.models.enums.TransactionType;
import api.models.utils.Checkers;

class CreateImpl implements ICreate{

	private String table;
	private LinkedHashMap<String,String>columns = new LinkedHashMap<String,String>();
	private IConnection<?> connection=null;
	private IPrimaryKey primaryKey;
	private boolean autoIncrement= false;
	private List<IForeignKey>foreignKeys = new ArrayList<>();
	@Override
	public ICreate setTable(String table) {
		
		this.table=table;
		return this;
	}

	@Override
	public ICreate addColumn(String column,String value) {
		Checkers.validateStringNotNull(column, "column");
		columns.put(column,value);
		return this;
	}

	@Override
	public boolean commit() {
		Checkers.validateStringNotNull(table, "table");
		if(!Checkers.isObjectNotNull(primaryKey)) {
			throw new ColumnsIsNullException("The Primakey Key is not defined");
		}
		if(columns == null && primaryKey == null) {
			throw new ColumnsIsNullException();
		}
		if(columns.isEmpty()&& primaryKey == null) {
			throw new ColumnsIsNullException();
		}
		
		
		CommitActionImpl commitAction = new CommitActionImpl();
		commitAction.setConnection(connection);
		commitAction.setColumns(columns);
		commitAction.setType(TransactionType.CREATE_TABLE);
		commitAction.setTable(table);
		commitAction.setPrimaryKey(this.primaryKey);
		commitAction.setForeignKeys(foreignKeys);
		return commitAction.commit();
	}

	@Override
	public ICreate setConnection(IConnection<?> connection) {
		this.connection=connection;
		return this;
		
	}

	@Override
	public ICreate addColumn(String column, String value, ICreateAtributes<?> createAtribute) {
		Checkers.validateStringNotNull(column, "column");
		String valueLower= value.toLowerCase();
		if(valueLower.contains("primary key") || valueLower.contains("primary_key") || valueLower.contains("primary")) {
			throw new TypeIsNotAllowed();
		}
		if(valueLower.contains("auto increment") || valueLower.contains("auto_increment")) {
			throw new TypeIsNotAllowed();
		}
		if(valueLower.contains("unique")) {
			throw new TypeIsNotAllowed();
		}
		
		if(createAtribute.getType() == CreateAtributes.PRIMARY_KEY) {
			IPrimaryKey primaryKey = (IPrimaryKey)createAtribute;
			
			if(Checkers.isObjectNotNull(this.primaryKey)) {
				throw new DuplicatePrimaryKeyException();
			}
				
			if(Checkers.isEmpty(primaryKey.getColumnName())) {
				primaryKey.setColumnName(column);
			}
			primaryKey.setValue(value);
			this.primaryKey = primaryKey;
			//this.autoIncrement=primaryKey.isAutoIncrement();
			
			return this;
		}
		
		if(createAtribute.getType() == CreateAtributes.UNIQUE) {
			columns.put(column,value);
			return this;
		}
		if(createAtribute.getType() == CreateAtributes.FOREIGN_KEY) {
			IForeignKey  foreignKey = (ForeignKey)createAtribute;
			Checkers.validateStringNotNull(foreignKey.getColumnForeign(), "columnForeign");
			Checkers.validateStringNotNull(foreignKey.getTableForeign(), "tableForeign");
			if(Checkers.isEmpty(foreignKey.getColumnName())) {
				
				foreignKey.setColumnName(column);
			
			}
			
			foreignKeys.add((ForeignKey)createAtribute);
			columns.put(column,value);
			return this;
		}
		
		
		return this;
	}

	/**
	 * @return the columns
	 */
	public LinkedHashMap<String, String> getColumns() {
		return columns;
	}

	/**
	 * @param columns the columns to set
	 */
	public void setColumns(LinkedHashMap<String, String> columns) {
		this.columns = columns;
	}

	/**
	 * @return the primaryKey
	 */
	public IPrimaryKey getPrimaryKey() {
		return primaryKey;
	}

	/**
	 * @param primaryKey the primaryKey to set
	 */
	public void setPrimaryKey(IPrimaryKey primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * @return the autoIncrement
	 */
	public boolean isAutoIncrement() {
		return autoIncrement;
	}

	/**
	 * @return the foreignKeys
	 */
	public List<IForeignKey> getForeignKeys() {
		return foreignKeys;
	}

	/**
	 * @param foreignKeys the foreignKeys to set
	 */
	public void setForeignKeys(List<IForeignKey> foreignKeys) {
		this.foreignKeys = foreignKeys;
	}

	/**
	 * @return the table
	 */
	public String getTable() {
		return table;
	}

	/**
	 * @return the connection
	 */
	public IConnection<?> getConnection() {
		return connection;
	}
	
	

}
