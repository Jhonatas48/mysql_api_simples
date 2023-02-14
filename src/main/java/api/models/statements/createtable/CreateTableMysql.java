package api.models.statements.createtable;

import java.util.Map;

import api.connection.impl.SQLBuildManager;
import api.interfaces.IForeignKey;
import api.models.enums.ConnectionType;
import api.models.enums.TransactionType;
import api.models.statements.Create;
import api.models.statements.interfaces.SQLBuild;

public class CreateTableMysql implements SQLBuild<Create> {

	public CreateTableMysql() {
		SQLBuildManager.addBuildSQL(this);
	}
	@Override
	public boolean checkType(ConnectionType connectionType, TransactionType transactionType, Object objectType) {
		
		if(!(objectType instanceof Create)) {
			
			return false;
		}
		
		if (TransactionType.CREATE_TABLE == transactionType && connectionType == ConnectionType.MYSQL) {
			return true;
		}

		return false;
	}
	
	@Override
	public String buildSQL(Create objectType) {
		String sql = "CREATE TABLE IF NOT EXISTS "+objectType.getTable()+"(";
		
		int size = objectType.getColums().size();
		int counter =0;
		
		for(Map.Entry<String,Object>value:objectType.getColums().entrySet()) {
			
			if((size-1) ==counter) {
				sql = sql+value.getKey()+" "+value.getValue()+")";
			}else {
				sql = sql+value.getKey()+" "+value.getValue()+",";
			}
			counter++;
		}
		counter=0;
		size=objectType.getForeignKeys().size();
	
		if(size ==0) {
			sql+=")";
			return sql;
		}
		
		for(IForeignKey foreignKey:objectType.getForeignKeys()) {
			if((size-1) ==counter) {
				
				sql = sql+",FOREIGN KEY("+foreignKey.getColumnName()+") REFERENCES "+foreignKey.getTableForeign()+"("+foreignKey.getColumnForeign()+"))";
			
			}else {
				sql = sql+",FOREIGN KEY(\""+foreignKey.getColumnName()+"\") REFERENCES \""+foreignKey.getTableForeign()+"\"(\""+foreignKey.getColumnForeign()+"\")";
			}
			counter++;
		}
		return sql;
	}
	@Override
	public String buildSQL(String... data) {
		
		return null;
	}

	
		

}
