package api.models;

import java.util.LinkedHashMap;

import api.interfaces.IInsert;
import api.models.enums.TransactionType;

class InsertImpl implements IInsert{

	private String table;
	private LinkedHashMap<String,String>columns = new LinkedHashMap<>();
	@Override
	public IInsert setTable(String table) {
		this.table=table;
		return this;
	}
	@Override
	public IInsert addColumn(String column, String value) {
		this.columns.put(column, value);
		return this;
	}
	@Override
	public boolean commit() {
	    CommitActionImpl commitAction = new CommitActionImpl();
	    commitAction.setTable(table);
	    commitAction.setType(TransactionType.INSERT);
	    commitAction.setColumns(columns);
	    return commitAction.commit();
		
	}
	
	

}
