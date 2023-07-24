package api.models.statements.createtable;

import java.util.Map;

import api.connection.impl.SQLBuildManager;
import api.exception.ColumnsIsNullException;
import api.exception.TypeIsNotAllowed;
import api.interfaces.IForeignKey;
import api.interfaces.IUnique;
import api.models.enums.ConnectionType;
import api.models.enums.TransactionType;
import api.models.statements.Create;
import api.models.statements.interfaces.SQLBuild;
import api.models.utils.Checkers;

public class CreateTableSQLITE implements SQLBuild<Create> {
	
	
	public CreateTableSQLITE() {
		
		SQLBuildManager.addBuildSQL(this);
	}

	@Override
	public boolean checkType(ConnectionType connectionType, TransactionType transactionType, Object objectType) {
		
		if(!(objectType instanceof Create)) {
			
			return false;
		}
		
		if (TransactionType.CREATE_TABLE == transactionType && ConnectionType.SQLITE == connectionType) {
			return true;
		}

		return false;
	}

	
	@Override
	public String buildSQL(Create objectType) { 
		if(!Checkers.isObjectNotNull(objectType.getPrimaryKey())) {
			throw new ColumnsIsNullException("The Primakey Key is not defined");
		}
		if(!objectType.getPrimaryKey().getValue().toLowerCase().equals("integer") && objectType.getPrimaryKey().isAutoIncrement()) {
			throw new TypeIsNotAllowed("AUTOINCREMENT is only allowed on type INTEGER");
		}
		String sql = "CREATE TABLE IF NOT EXISTS "+objectType.getTable()+"(";
		//sql = sql.replace("/table/", objectType.getTable());
		int size = objectType.getColums().size();
		int counter =0;
		sql+=objectType.getPrimaryKey().getColumnName()+" "+objectType.getPrimaryKey().getValue()+" ";
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
			
			sql+=",PRIMARY KEY(\""+objectType.getPrimaryKey().getColumnName()+"\"";
			sql+=objectType.getPrimaryKey().isAutoIncrement()==true?" AUTOINCREMENT))":")";
			return sql;
		}
		for(IForeignKey foreignKey:objectType.getForeignKeys()) {
			if((size-1) ==counter) {
				
				sql = sql+",FOREIGN KEY(\""+foreignKey.getOriginColumnName()+"\") REFERENCES \""+foreignKey.getTableForeign()+"\"(\""+foreignKey.getColumnForeign()+"\")";
			
			}else {
				sql = sql+",FOREIGN KEY(\""+foreignKey.getOriginColumnName()+"\") REFERENCES \""+foreignKey.getTableForeign()+"\"(\""+foreignKey.getColumnForeign()+"\")";
			}
			counter++;
		}
		for(IUnique uniqueKeys : objectType.getUniqueKeys()) {
			sql+= ",UNIQUE("+uniqueKeys.getColumns()+")";
		}
		sql+=",PRIMARY KEY(\""+objectType.getPrimaryKey().getColumnName()+"\"";
		sql+=objectType.getPrimaryKey().isAutoIncrement()==true?" AUTOINCREMENT))":")";
		
		return sql;
	}

	@Override
	public String buildSQL(String... data) {
		
		return null;
	}

	
	

}
