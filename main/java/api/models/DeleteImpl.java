package api.models;

import api.connection.ConnectionManager;
import api.interfaces.ICommitAction;
import api.interfaces.actions.IDelete;
import api.models.enums.TransactionType;

class DeleteImpl implements IDelete {

	private String table;
	private ConnectionManager connection=null;
	@Override
	public IDelete setTable(String table) {
		this.table=table;
		return this;
	}

	@Override
	public ICommitAction filter(String filter) {
		CommitActionImpl commitAction = new CommitActionImpl();
		commitAction.setTable(table);
		commitAction.setFilter(filter);
		commitAction.setType(TransactionType.DELETE);
		commitAction.setConnectionManager(connection);
		return commitAction;
	}

	@Override
	public IDelete setConnectionManager(ConnectionManager connectionManager) {
		// TODO Auto-generated method stub
		this.connection = connectionManager;
		return this;
	}

	public ConnectionManager getConnectionManager() {
		return connection;
	}


}
