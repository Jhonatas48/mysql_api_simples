package api.models;

import java.util.LinkedHashMap;

import api.connection.ConnectionManager;
import api.interfaces.ICommitAction;
import api.interfaces.actions.IUpdate;
import api.models.enums.TransactionType;
import api.models.utils.Checkers;

class UpdateImpl implements IUpdate {
	private String table;
	private ConnectionManager connection=null;
	private LinkedHashMap<String,String>columns = new LinkedHashMap<String,String>();
	@Override
	public IUpdate setTable(String table) {
		
		this.table = table;
		return this;
	}

	@Override
	public ICommitAction filter(String filter) {
		CommitActionImpl commitAction = new CommitActionImpl();
		commitAction.setTable(table);
		commitAction.setFilter(filter);
		commitAction.setType(TransactionType.UPDATE);
		commitAction.setColumns(columns);
		commitAction.setConnectionManager(connection);
		return commitAction;
	}

	@Override
	public IUpdate addColumn(String column, String value) {
		Checkers.validateStringNotNull(column,"Column");
		Checkers.validateStringNotNull(value, "value");
		columns.put(column, value);
		return this;
		
	}
	
	@Override
	public IUpdate setConnectionManager(ConnectionManager connectionManager) {
		this.connection=connectionManager;
		return this;
		
	}

	public ConnectionManager getConnectionManager() {
		return connection;
	}
	

}
