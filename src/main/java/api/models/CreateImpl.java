package api.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import api.connection.ConnectionManager;
import api.exception.ColumnsIsNullException;
import api.exception.DuplicatePrimaryKeyException;
import api.exception.TypeIsNotAllowed;
import api.interfaces.actions.ICreate;
import api.interfaces.connection.IConnection;
import api.interfaces.createatrrubutes.ICreateAtributes;
import api.interfaces.createatrrubutes.IForeignKey;
import api.interfaces.createatrrubutes.IPrimaryKey;
import api.interfaces.createatrrubutes.IUnique;
import api.models.atributes.ForeignKey;
import api.models.enums.CreateAtributes;
import api.models.enums.TransactionType;
import api.models.utils.Checkers;

class CreateImpl implements ICreate{

	private String table;
	private LinkedHashMap<String,Object>columns = new LinkedHashMap<String,Object>();
	private IConnection<?> connection=null;
	private ConnectionManager connectionManager = null;
	private IPrimaryKey primaryKey;
	private boolean autoIncrement= false;
	private List<IForeignKey>foreignKeys = new ArrayList<>();
	private List<IUnique>uniqueKeys = new ArrayList<>();
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
	public void commitAsync(Consumer<Boolean>action,Consumer<Throwable>error) {
		Checkers.validadeObjectNotNull(action, "action");
	
	try {

	CommitActionImpl commitAction = new CommitActionImpl();
	
	commitAction.setConnection(connection);
	
	commitAction.setColumns(columns);
	
	commitAction.setType(TransactionType.CREATE_TABLE);
	
	commitAction.setTable(table);
	
	commitAction.setPrimaryKey(this.primaryKey);
	
	commitAction.setForeignKeys(foreignKeys);
	
	commitAction.setUniqueKeys(this.uniqueKeys);

	commitAction.setConnectionManager(this.connectionManager);
	 
		 commitAction.commitAsync(action,error);
		
		if(error != null && commitAction.getGetErrorException() != null) {
		   error.accept(commitAction.getGetErrorException());	
		}
		
		
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}


  }
	@Override
	public void commitAsync(Consumer<Boolean>action) {
		commitAsync(action, null);
	}
	@Override
	public boolean commit(Consumer<? super Throwable> failure) {
		
		if(!Checkers.isObjectNotNull(primaryKey)) {
			throw new ColumnsIsNullException("The Primakey Key is not defined");
		}
		
		if(columns == null && primaryKey == null) {
			throw new ColumnsIsNullException();
		}
		
		if(columns.isEmpty()&& primaryKey == null) {
			throw new ColumnsIsNullException();
		}
		try {
	
		CommitActionImpl commitAction = new CommitActionImpl();
	
		commitAction.setConnection(connection);
	
		commitAction.setColumns(columns);
		
		commitAction.setType(TransactionType.CREATE_TABLE);
		
		commitAction.setTable(table);
		
		commitAction.setPrimaryKey(this.primaryKey);
		
		commitAction.setForeignKeys(foreignKeys);
		
		commitAction.setUniqueKeys(this.uniqueKeys);
		
		commitAction.setConnectionManager(this.connectionManager);
		
			boolean result = commitAction.commit();
			 
			if(failure != null && commitAction.getGetErrorException() != null) {
			  failure.accept(commitAction.getGetErrorException());	
			}
			
			return result;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		return false;
	}
	
	@Override
	public boolean commit() {
		return this.commit(null);
	}

	@Override
	public ICreate setConnection(IConnection<?> connection) {
		this.connection=connection;
		return this;
		
	}
	
	@Override
	public ICreate setConnectionManager(ConnectionManager connectionManager) {
		Checkers.validadeObjectNotNull(connectionManager, "connectionManager");
		this.connectionManager = connectionManager;
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
			IUnique uniqueKey = (IUnique)createAtribute.getAtribute();
			if(uniqueKey.getColumnsQuantity() == 0) {
				uniqueKey.addColumn(column);
			}
			this.uniqueKeys.add(uniqueKey);
			return this;
		}
		if(createAtribute.getType() == CreateAtributes.FOREIGN_KEY) {
			IForeignKey  foreignKey = (ForeignKey)createAtribute;
			Checkers.validateStringNotNull(foreignKey.getColumnForeign(), "columnForeign");
			Checkers.validateStringNotNull(foreignKey.getTableForeign(), "tableForeign");
			if(Checkers.isEmpty(foreignKey.getOriginColumnName())) {
				
				foreignKey.setOriginColumnName(column);
			
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
	public LinkedHashMap<String, Object> getColumns() {
		return columns;
	}

	/**
	 * @param columns the columns to set
	 */
	public void setColumns(LinkedHashMap<String, Object> columns) {
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
