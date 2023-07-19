package com.github.jhonatas48.api.models;

import com.github.jhonatas48.api.interfaces.ICommitAction;
import com.github.jhonatas48.api.interfaces.IDelete;
import com.github.jhonatas48.api.models.enums.TransactionType;

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
