package com.github.jhonatas48.api.models.statements;

import com.github.jhonatas48.api.models.utils.Checkers;

public class Delete {
	

	private String table;
	private String filter;
	
	public Delete( String table,String filter) {
		this.table = table;
		this.filter = filter;
		validation();
		
	}
	private void validation() {
		
		Checkers.validateStringNotNull(table,"table");
		
		Checkers.validateStringNotNull(filter,"filter");
		
	}
	public Delete() {

	}
	public String getTable() {
		validation();
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getFilter() {
		validation();
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	
}
