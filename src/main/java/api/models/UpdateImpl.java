package api.models;

import java.util.LinkedHashMap;

import api.interfaces.ICommitAction;
import api.interfaces.IUpdate;
import api.models.enums.TransactionType;
import api.models.utils.Checkers;

class UpdateImpl implements IUpdate {
	private String table;
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
		return commitAction;
	}

	@Override
	public IUpdate addColumn(String column, String value) {
		Checkers.validateStringNotNull(column,"Column");
		Checkers.validateStringNotNull(value, "value");
		columns.put(column, value);
		return this;
		
	}
	

}
