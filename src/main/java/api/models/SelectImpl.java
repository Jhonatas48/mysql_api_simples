package api.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import api.interfaces.ISelect;
import api.models.statements.Result;
import api.models.utils.Checkers;

class SelectImpl extends PerformTransaction implements ISelect {
	private String table;
	private String filter;
	private List<String>columns = new ArrayList<>();
	private boolean useTableLog;
	private LinkedHashMap<String,String>tableInnerJoin=new LinkedHashMap<>();
	@Override
	public ISelect setTable(String table) {
		this.table= table;
		return this;
	}

	public Result queryResult() {
		return queryResult(false);
	}

	@Override
	public Result queryResult(boolean useLogTransaction)
	{
		
		String columnsSelect = "";
		int count=0;
		for(String column:columns) {
			
			if(count == (columns.size()-1)) {
				columnsSelect+=""+column;
			}else {
				columnsSelect+=""+column+",";
			}
			
			count++;
		}
		
		if(tableInnerJoin.isEmpty()) {
			
			return rowSelect(table, columnsSelect,filter,useLogTransaction);
		}
		String[] tableArguments = table.split(" ");
		
		if(tableArguments.length < 2) {
			System.out.println("Faltou passar a variavel");
			return null;
		}
		String innerJoins = "";
		for(Entry<String,String>innerjoin:tableInnerJoin.entrySet()) {
			innerJoins = " inner join "+innerjoin.getKey()+" on "+innerjoin.getValue();
		}
		return rowSelect(table+innerJoins, columnsSelect,filter,useLogTransaction);
	}

	@Override
	public ISelect addColumn(String column) {
	
		columns.add(column);
		return this;
	}

	public boolean isUseTableLog() {
		return useTableLog;
	}

	public void setUseTableLog(boolean useTableLog) {
		this.useTableLog = useTableLog;
	}

	@Override
	public ISelect addInnerjoin(String table,String onClause) {
		
		Checkers.validateStringNotNull(table,"table");
		Checkers.validateStringNotNull(onClause, "onClause");
		
		this.tableInnerJoin.put(table,onClause);
		
		return this;
	}

	@Override
	public ISelect filter(String filter) {
		this.filter=filter;
		return this;
	}

}
