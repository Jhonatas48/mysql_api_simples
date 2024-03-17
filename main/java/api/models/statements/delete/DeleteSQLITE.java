package api.models.statements.delete;

import api.connection.sqlbuilder.SQLBuildManager;
import api.models.enums.ConnectionType;
import api.models.enums.TransactionType;
import api.models.statements.Delete;
import api.models.statements.interfaces.SQLBuild;

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
