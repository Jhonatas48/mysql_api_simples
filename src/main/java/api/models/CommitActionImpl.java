package api.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Consumer;

import api.connection.IConnection;
import api.connection.impl.SQLBuildManager;
import api.exception.ColumnsIsNullException;
import api.exception.FilterIsNullException;
import api.exception.TransactionInvalidException;
import api.interfaces.ICommitAction;
import api.interfaces.IForeignKey;
import api.interfaces.IPrimaryKey;
import api.models.enums.TransactionType;
import api.models.statements.Create;
import api.models.statements.Delete;
import api.models.statements.Insert;
import api.models.statements.Update;
import api.models.statements.UpdateValue;
import api.models.utils.Checkers;

class CommitActionImpl extends PerformTransaction implements ICommitAction {

	private String table;
	private String filter;
	private TransactionType type;
	private LinkedHashMap<String,String>columns = new LinkedHashMap<>();
	private IConnection<?> connection = null;
	private IPrimaryKey  primaryKey;
	private boolean autoIncrement= false;
	private List<IForeignKey>foreignKeys = new ArrayList<>();

	@Override
	public boolean commit(Consumer<? super Throwable> failure) {
		
		Checkers.validateStringNotNull(table, "table");
		if(type==TransactionType.SELECT) {
			throw new TransactionInvalidException();
		}
		
		if((type == TransactionType.UPDATE || type == TransactionType.DELETE)&& Checkers.isEmpty(filter)) {
			
			throw new FilterIsNullException();
			
		}
		
		if((type == TransactionType.CREATE_TABLE || type == TransactionType.INSERT  || type == TransactionType.UPDATE) && checkEmptyColumns()) {
			
			throw new ColumnsIsNullException();
		}
		boolean result = false;
	    switch (type.toString().toUpperCase()) {
		case "CREATE_TABLE":
			Create create = new Create();
			create.setTable(table);
			create.setForeignKeys(foreignKeys);
			create.setPrimaryKey(primaryKey);
			columns.entrySet().forEach(column->{
				create.addColumn(column.getKey(), column.getValue());
			});
			
			if(connection != null) {
				return createTable(SQLBuildManager.buildSQL(connection.geConnectionType(),TransactionType.CREATE_TABLE, create), connection);
			}
			result= createTable(create);
			break;
		case "INSERT":
			Insert insert = new Insert();
			insert.setTable(table);
		
			List<String> values = new ArrayList<>();
			String columnsInsert= "";
			int count=0;
			
			for(Entry<String,String> column:columns.entrySet())
			{
				count++;
				if(count == columns.size()) {
					columnsInsert+=column.getKey();
				}else {
					columnsInsert+=column.getKey()+",";
				}
				
				values.add(column.getValue());
			}
			
			insert.setColumns(columnsInsert);
			insert.setData(values);

			result= tableInsert(insert);			
			break;
	    case "UPDATE":
	    	UpdateValue updateValue = new UpdateValue();
	    	for(Entry<String,String> columnUpdate:columns.entrySet())
			{
	    		updateValue.add(columnUpdate.getKey(),columnUpdate.getValue());
			}
	    	
	    	Update update = new Update(table, updateValue,filter);
	    	
	    	
	       result= rowUpdate(update);
	       break;
	    case "DELETE":
	    	
	    	Delete delete = new Delete(table,filter);
	    	
	    	
	    	result= rowDelete(delete);
	    	break;
	    }
	    if(failure != null && getGetErrorException() != null) {
	    	failure.accept(getGetErrorException());
	    }
	    
		return result;
		
		
	}

	@Override
	public boolean commit() {
		return this.commit(null);
	
	}
	
	private boolean checkEmptyColumns() {
		
		if(type == TransactionType.CREATE_TABLE && primaryKey == null && columns == null) {
			return true;
		}
		
		if(type == TransactionType.CREATE_TABLE && primaryKey == null && columns.isEmpty()) {
			return true;
		}
		if(type == TransactionType.CREATE_TABLE) {
			return false;
		}
		
		if(columns == null) {
			
			return true;
		}
	
		return columns.isEmpty();
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	/**
	 * @return Transaction Type
	 */
	public TransactionType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	protected void setType(TransactionType type) {
		this.type = type;
	}
	
	/**
	 * 
	 * */
	public LinkedHashMap<String,String> getColumns() {
		return columns;
	}

	protected void setColumns(LinkedHashMap<String,String> columns) {
		this.columns = columns;
	}

	public IConnection<?> getConnection() {
		return connection;
	}

	protected void setConnection(IConnection<?> connection) {
		this.connection = connection;
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
	protected void setPrimaryKey(IPrimaryKey primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * @return the autoIncrement
	 */
	public boolean isAutoIncrement() {
		return autoIncrement;
	}

	/**
	 * @param autoIncrement the autoIncrement to set
	 */
	protected void setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
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
	protected void setForeignKeys(List<IForeignKey> foreignKeys) {
		this.foreignKeys = foreignKeys;
	}

	
	public Exception getException() {
	
		return getGetErrorException();
	}


	

}
