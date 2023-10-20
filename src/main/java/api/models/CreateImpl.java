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
	System.out.println("-----------------");
	System.out.println("commitAction");
	CommitActionImpl commitAction = new CommitActionImpl();
	System.out.println("commitAction Connection");
	commitAction.setConnection(connection);
	System.out.println("commitAction Columns");
	commitAction.setColumns(columns);
	System.out.println("commitAction TransactionType");
	commitAction.setType(TransactionType.CREATE_TABLE);
	System.out.println("commitAction Table");
	commitAction.setTable(table);
	System.out.println("commitAction PrimaryKey");
	commitAction.setPrimaryKey(this.primaryKey);
	System.out.println("commitAction ForeignKey");
	commitAction.setForeignKeys(foreignKeys);
	System.out.println("commitAction Unique");
	commitAction.setUniqueKeys(this.uniqueKeys);
	System.out.println("commitAction ConnectionManager");
	commitAction.setConnectionManager(this.connectionManager);
	 System.out.println("commit");
		 commitAction.commitAsync(action,error);
		 System.out.println("saiu commit");
		if(error != null && commitAction.getGetErrorException() != null) {
		   error.accept(commitAction.getGetErrorException());	
		}
		 System.out.println("saiu");
		
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
		 System.out.println("entrou");
		Checkers.validateStringNotNull(table, "table");
		System.out.println("-----------------");
		if(!Checkers.isObjectNotNull(primaryKey)) {
			throw new ColumnsIsNullException("The Primakey Key is not defined");
		}
		System.out.println("-----------------");
		if(columns == null && primaryKey == null) {
			throw new ColumnsIsNullException();
		}
		System.out.println("-----------------");
		if(columns.isEmpty()&& primaryKey == null) {
			throw new ColumnsIsNullException();
		}
		try {
		System.out.println("-----------------");
		System.out.println("commitAction");
		CommitActionImpl commitAction = new CommitActionImpl();
		System.out.println("commitAction Connection");
		commitAction.setConnection(connection);
		System.out.println("commitAction Columns");
		commitAction.setColumns(columns);
		System.out.println("commitAction TransactionType");
		commitAction.setType(TransactionType.CREATE_TABLE);
		System.out.println("commitAction Table");
		commitAction.setTable(table);
		System.out.println("commitAction PrimaryKey");
		commitAction.setPrimaryKey(this.primaryKey);
		System.out.println("commitAction ForeignKey");
		commitAction.setForeignKeys(foreignKeys);
		System.out.println("commitAction Unique");
		commitAction.setUniqueKeys(this.uniqueKeys);
		System.out.println("commitAction ConnectionManager");
		commitAction.setConnectionManager(this.connectionManager);
		 System.out.println("commit");
			boolean result = commitAction.commit();
			 System.out.println("saiu commit");
			if(failure != null && commitAction.getGetErrorException() != null) {
			  failure.accept(commitAction.getGetErrorException());	
			}
			 System.out.println("saiu");
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
