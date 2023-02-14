package api.models.statements.update;

import api.connection.impl.SQLBuildManager;
import api.models.enums.ConnectionType;
import api.models.enums.TransactionType;
import api.models.statements.Update;
import api.models.statements.interfaces.SQLBuild;

public class UpdateMysql implements SQLBuild<Update>{
	
	
	public UpdateMysql() {
		
		SQLBuildManager.addBuildSQL(this);
	}

	@Override
	public boolean checkType(ConnectionType connectionType, TransactionType transactionType, Object objectType) {
		
		if(!(objectType instanceof Update)) {
			
			return false;
		}
		
		if (TransactionType.UPDATE == transactionType && ConnectionType.MYSQL == connectionType) {
			return true;
		}
		
		return false;
	}

	@Override
	public String buildSQL(Update objectType) {
		
		return null;
	}

	@Override
	public String buildSQL(String... data) {
		
		return null;
	}
	
	
}
