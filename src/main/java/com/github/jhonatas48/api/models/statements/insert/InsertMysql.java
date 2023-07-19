package com.github.jhonatas48.api.models.statements.insert;

import com.github.jhonatas48.api.connection.impl.SQLBuildManager;
import com.github.jhonatas48.api.models.enums.ConnectionType;
import com.github.jhonatas48.api.models.enums.TransactionType;
import com.github.jhonatas48.api.models.statements.Insert;
import com.github.jhonatas48.api.models.statements.interfaces.SQLBuild;

public class InsertMysql implements SQLBuild<Insert>{
	
	
	public InsertMysql() {
		SQLBuildManager.addBuildSQL(this);
	}

	@Override
	public boolean checkType(ConnectionType connectionType, TransactionType transactionType, Object objectType) {
		
		if(!(objectType instanceof Insert)) {
			
			return false;
		}
		
		if (TransactionType.INSERT == transactionType && ConnectionType.MYSQL == connectionType) {
			return true;
		}
		
		return false;
	}

	@Override
	public String buildSQL(Insert objectType) {
		
		String sql = "Insert into "+objectType.getTable()+"("+objectType.getColumns()+")VALUES("; 
		
		int counter =  objectType.getData().length;
		for (int id=0;id<counter;id++) {
		
			if(id==(counter-1)) {
				sql=sql+"?)";
			}else {
				sql=sql+"?,";
			}
		}
             
		//System.out.println("MYSQL: "+sql);
		return sql;
	}

	@Override
	public String buildSQL(String... data) {
		
		return null;
	}

	

	
}
