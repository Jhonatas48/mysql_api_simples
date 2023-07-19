package com.github.jhonatas48.api.models.statements.delete;

import com.github.jhonatas48.api.connection.impl.SQLBuildManager;
import com.github.jhonatas48.api.models.enums.ConnectionType;
import com.github.jhonatas48.api.models.enums.TransactionType;
import com.github.jhonatas48.api.models.statements.Delete;
import com.github.jhonatas48.api.models.statements.interfaces.SQLBuild;

public class DeleteSQLITE implements SQLBuild<Delete>{
	
	
	public DeleteSQLITE() {
		
		SQLBuildManager.addBuildSQL(this);
	}

	@Override
	public boolean checkType(ConnectionType connectionType, TransactionType transactionType, Object objectType) {
		
		if(!(objectType instanceof Delete)) {
			
			return false;
		}
		
		if (TransactionType.DELETE == transactionType && ConnectionType.SQLITE == connectionType) {
			return true;
		}
		
		return false;
	}

	@Override
	public String buildSQL(Delete objectType) {
	
		String sql = "DELETE FROM "+objectType.getTable()+" WHERE "+objectType.getFilter();
		
		return sql;
           
	}

	@Override
	public String buildSQL(String... data) {
		
		
		return null;
	}

}
