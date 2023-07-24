package api.models.statements.update;

import api.connection.impl.SQLBuildManager;
import api.models.enums.ConnectionType;
import api.models.enums.TransactionType;
import api.models.statements.Update;
import api.models.statements.interfaces.SQLBuild;

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
