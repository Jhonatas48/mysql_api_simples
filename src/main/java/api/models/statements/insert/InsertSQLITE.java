package api.models.statements.insert;

import api.connection.sqlbuilder.SQLBuildManager;
import api.models.enums.ConnectionType;
import api.models.enums.TransactionType;
import api.models.statements.Insert;
import api.models.statements.interfaces.SQLBuild;

public class InsertSQLITE implements SQLBuild<Insert>{
	
	
	public InsertSQLITE() {
		
		SQLBuildManager.addBuildSQL(this);
	}

	@Override
	public boolean checkType(ConnectionType connectionType, TransactionType transactionType, Object objectType) {
		
	
		if(!(objectType instanceof Insert)) {
		
			return false;
		}
		
		if (TransactionType.INSERT == transactionType && ConnectionType.SQLITE == connectionType) {
			
			return true;
		}
		return false;
	}

	@Override
	public String buildSQL(Insert objectType) {
		// TODO Auto-generated method stub
		InsertMysql mysql = new InsertMysql();
	
		return mysql.buildSQL(objectType);
	}

	@Override
	public String buildSQL(String... data) {
		// TODO Auto-generated method stub
		return null;
	}


	
	

}
