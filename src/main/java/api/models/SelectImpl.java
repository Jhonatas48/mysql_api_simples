package api.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import api.connection.ConnectionManager;
import api.interfaces.actions.ISelect;
import api.models.statements.Result;
import api.models.statements.Row;
import api.models.utils.Checkers;
import api.models.utils.Transformers;

class SelectImpl extends PerformTransaction implements ISelect {
	private String table;
	private String filter;
	private List<String>columns = new ArrayList<>();
	private boolean useTableLog;
	private ConnectionManager connection=null;
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

		setConnectionManagers(connection);
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
			System.out.println("Missed passing the variable that represents the table in the Inner Join");
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

	@Override
	public Exception getException() {
		
		return getGetErrorException();
	}
	
	@Override
	 public <T> void queryListAsync(Class<T> clazz,Consumer<List<T>>function) {
		
		Checkers.validadeObjectNotNull(function,"function");
		Checkers.validadeObjectNotNull(clazz,"clazz");
//			Checkers.validadeObjectNotNull(action, "action");

		connection.getAsyncManager().getService().submit(()->{
			try {
				List<T>list = queryList(clazz);
				
				function.accept(list);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			});
		}
	
	@Override
	public <T> List<T> queryList(Class<T> clazz) {
		
	    Result result = queryResult(false);
	   
	    if (!Checkers.isObjectNotNull(result) || Checkers.isListEmpty(result.getRows())) {
	    	
	    	return null;
	    }
	   
	    List<T> resultList = new ArrayList<>();
	    for (Row row : result.getRows()) {
	        try {
	            T instance = Transformers.instanceOf(clazz, row);
	            resultList.add(instance);
	        } catch (Exception e) {
	            // Lida com qualquer exceção ocorrida durante a conversão
	            e.printStackTrace();
	        }
	    }

	    return resultList;
	}
	
	@Override
	public <T> void queryResultAsync(Class<T>classz,Consumer<T>function) {
		
		Checkers.validadeObjectNotNull(function,"function");
		Checkers.validadeObjectNotNull(classz,"classz");
		
		connection.getAsyncManager().getService().submit(()->{
			try {
				T object  = queryResult(classz);
				
				function.accept(object);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			});
		
		
	}
	

	@Override
	public <T> T queryResult(Class<T> clazz) {
		 Result result = queryResult(false);

		    if (!Checkers.isObjectNotNull(result) || Checkers.isListEmpty(result.getRows())) {
		        return null;
		    }
		    
		    
		    try {
	            T instance = Transformers.instanceOf(clazz,result.getRows().get(0));
	           return instance;
	        } catch (Exception e) {
	            // Lida com qualquer exceção ocorrida durante a conversão
	            e.printStackTrace();

	    		return null;
	        }

	}
	
	@Override
	public ISelect setConnectionManager(ConnectionManager connectionManager) {
		this.connection=connectionManager;
		
		return this;
		
	}

	public ConnectionManager getConnectionManager() {
		return connection;
	}
    
}
