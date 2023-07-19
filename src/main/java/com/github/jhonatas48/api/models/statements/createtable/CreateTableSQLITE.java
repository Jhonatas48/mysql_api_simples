package com.github.jhonatas48.api.models.statements.createtable;

import java.util.Map;

import com.github.jhonatas48.api.connection.impl.SQLBuildManager;
import com.github.jhonatas48.api.exception.ColumnsIsNullException;
import com.github.jhonatas48.api.exception.TypeIsNotAllowed;
import com.github.jhonatas48.api.interfaces.IForeignKey;
import com.github.jhonatas48.api.interfaces.IUnique;
import com.github.jhonatas48.api.models.enums.ConnectionType;
import com.github.jhonatas48.api.models.enums.TransactionType;
import com.github.jhonatas48.api.models.statements.Create;
import com.github.jhonatas48.api.models.statements.interfaces.SQLBuild;
import com.github.jhonatas48.api.models.utils.Checkers;

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
