package com.github.jhonatas48.api.models.statements.update;

import com.github.jhonatas48.api.connection.impl.SQLBuildManager;
import com.github.jhonatas48.api.models.enums.ConnectionType;
import com.github.jhonatas48.api.models.enums.TransactionType;
import com.github.jhonatas48.api.models.statements.Update;
import com.github.jhonatas48.api.models.statements.interfaces.SQLBuild;

public class UpdateSQLITE implements SQLBuild<Update>{
	
	
	public UpdateSQLITE() {
		
		SQLBuildManager.addBuildSQL(this);
	}

	@Override
	public boolean checkType(ConnectionType connectionType, TransactionType transactionType, Object objectType) {
		
		if(!(objectType instanceof Update)) {
			
			return false;
		}
		
		if (TransactionType.UPDATE == transactionType && ConnectionType.SQLITE == connectionType) {
			return true;
		}
	
		return false;
	}

	@Override
	public String buildSQL(Update objectType) {
	
		String sql = "UPDATE "+objectType.getTable()+" SET ";
		
		int counter =  objectType.getValue().getKeys().size();
		int id = 0;
		for(String column: objectType.getValue().getKeys()) {
			
			if(id==(counter-1)) {
				
				sql=sql+column+"=?";
				
			}else {
				
				sql=sql+column+"=?,";
				
			}
			id ++;
		}
		sql= sql+" WHERE "+objectType.getFilter();
		
		return sql;
           
	}

	@Override
	public String buildSQL(String... data) {
		
		
		return null;
	}

}
