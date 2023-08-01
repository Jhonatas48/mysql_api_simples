package api.models.statements.createtable;

import java.util.Map;

import api.connection.sqlbuilder.SQLBuildManager;
import api.interfaces.createatrrubutes.IForeignKey;
import api.interfaces.createatrrubutes.IUnique;
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
		sql+=objectType.getPrimaryKey().getColumnName()+" "+objectType.getPrimaryKey().getValue();
		sql+=objectType.getPrimaryKey().isAutoIncrement()==true?" AUTO_INCREMENT":"";
        
		for(Map.Entry<String,Object>value:objectType.getColums().entrySet()) {
			
			if((size-1) ==counter) {
				sql = sql+","+value.getKey()+" "+value.getValue();
			}else {
				sql = sql+","+value.getKey()+" "+value.getValue();
			}
			counter++;
		}
		
		counter=0;
		size=objectType.getForeignKeys().size();
		int sizeUnique = objectType.getUniqueKeys().size();
		if(size ==0 && sizeUnique == 0) {
			sql+=",PRIMARY KEY("+objectType.getPrimaryKey().getColumnName()+"))";
			return sql;
		}
		
		for(IForeignKey foreignKey:objectType.getForeignKeys()) {
			if((size-1) ==counter) {
				
				sql = sql+",FOREIGN KEY("+foreignKey.getOriginColumnName()+") REFERENCES "+foreignKey.getTableForeign()+"("+foreignKey.getColumnForeign()+"))";
			
			}else {
				sql = sql+",FOREIGN KEY("+foreignKey.getOriginColumnName()+") REFERENCES "+foreignKey.getTableForeign()+"("+foreignKey.getColumnForeign()+")";
			}
			counter++;
		}
		
		for(IUnique uniqueKeys : objectType.getUniqueKeys()) {
			sql+= ",UNIQUE("+uniqueKeys.getColumns()+")";
		}
		sql+=",PRIMARY KEY("+objectType.getPrimaryKey().getColumnName()+"))";
		//sql+=")";
		return sql;
	}
	@Override
	public String buildSQL(String... data) {
		
		return null;
	}

	
		

}
