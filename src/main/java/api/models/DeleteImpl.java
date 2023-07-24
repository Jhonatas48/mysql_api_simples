package api.models;

import api.interfaces.ICommitAction;
import api.interfaces.IDelete;
import api.models.enums.TransactionType;

class DeleteImpl implements IDelete {

	private String table;
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
		return commitAction;
	}


}
