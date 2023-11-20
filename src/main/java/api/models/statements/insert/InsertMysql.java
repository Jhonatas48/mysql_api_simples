package api.models.statements.insert;



import api.connection.sqlbuilder.SQLBuildManager;
import api.models.enums.ConnectionType;
import api.models.enums.TransactionType;
import api.models.statements.Insert;
import api.models.statements.interfaces.SQLBuild;

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
             
		
		return sql;
	}

	@Override
	public String buildSQL(String... data) {
		
		return null;
	}

	

	
}
