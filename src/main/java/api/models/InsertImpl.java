package api.models;

import java.util.LinkedHashMap;
import java.util.function.Consumer;

import api.connection.ConnectionManager;
import api.interfaces.actions.IInsert;
import api.models.enums.TransactionType;
import api.models.utils.Checkers;

class InsertImpl implements IInsert{

	private String table;
	private LinkedHashMap<String,Object>columns = new LinkedHashMap<>();
	private ConnectionManager connectionManager;
	@Override
	public IInsert setTable(String table) {
		this.table=table;
		return this;
	}
	@Override
	public IInsert addColumn(String column, Object value) {
		this.columns.put(column, value);
		return this;
	}
	@Override
	public boolean commit() {
	   
		return this.commit(null);
		
	}
	@Override
	public boolean commit(Consumer<? super Throwable> failure) {
		// TODO Auto-generated method stub
		 CommitActionImpl commitAction = new CommitActionImpl();
		    commitAction.setTable(table);
		    commitAction.setType(TransactionType.INSERT);
		    commitAction.setColumns(columns);
		    commitAction.setConnectionManager(connectionManager);
		    
		   boolean result=  commitAction.commit();
		   if(failure !=null && commitAction.getGetErrorException() != null) {
			   failure.accept(commitAction.getGetErrorException());
		   }
		   
		return result;
	}
	@Override
	public IInsert setConnectionManager(ConnectionManager connection) {
		Checkers.validadeObjectNotNull(connection, "connectionManager");
		this.connectionManager = connection;
		return this;
	}
	
	
	

}
