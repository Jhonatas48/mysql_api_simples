package com.github.jhonatas48.api.connection.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.jhonatas48.api.models.enums.ConnectionType;
import com.github.jhonatas48.api.models.enums.TransactionType;
import com.github.jhonatas48.api.models.statements.createtable.CreateTableMysql;
import com.github.jhonatas48.api.models.statements.createtable.CreateTableSQLITE;
import com.github.jhonatas48.api.models.statements.delete.DeleteMysql;
import com.github.jhonatas48.api.models.statements.delete.DeleteSQLITE;
import com.github.jhonatas48.api.models.statements.insert.InsertMysql;
import com.github.jhonatas48.api.models.statements.insert.InsertSQLITE;
import com.github.jhonatas48.api.models.statements.interfaces.SQLBuild;
import com.github.jhonatas48.api.models.statements.update.UpdateMysql;
import com.github.jhonatas48.api.models.statements.update.UpdateSQLITE;

public class SQLBuildManager  {

	@SuppressWarnings("rawtypes")
	private static List<SQLBuild>listSQLBuild=new ArrayList<>();
	
	
	public SQLBuildManager() {
		
		initializeCRUD();
	
		
	}
	
	private static void initializeCRUD() {
		new CreateTableSQLITE();
		new CreateTableMysql();
		new InsertMysql();
		new InsertSQLITE();
		new UpdateMysql();
		new UpdateSQLITE();
		new DeleteMysql();
		new DeleteSQLITE();
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public static String buildSQL(ConnectionType connectionType,TransactionType transactionType, Object objectType) {
		
		if(listSQLBuild.size()==0) {
			initializeCRUD();
		}
		
		String sql = null;
		
		for(SQLBuild sqlBuild: listSQLBuild) {
		
			if(sqlBuild.checkType(connectionType, transactionType, objectType))
			{
				sql= sqlBuild.buildSQL(objectType);
			}
			if(sql != null) {
				return sql;
			}
			
		}
		
		if(sql ==null) {
			
			throw new NullPointerException("String SQL nao encontrada!");
		}
		
		return sql;
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public static String buildSQL(ConnectionType connectionType , TransactionType transactionType, String... data) {
		
		String sql = null;
		for(SQLBuild sqlBuild: listSQLBuild) {
			
			sql= sqlBuild.buildSQL(data);
			if(sql != null) {
				return sql;
			}
			
		}
		
		if(sql ==null) {
			
			throw new NullPointerException("String SQL nao encontrada!");
		}
		
		return null;
	}

	
	@SuppressWarnings("rawtypes")
	public static void addBuildSQL(SQLBuild SQLBuild) {
		
		
		listSQLBuild.add(SQLBuild);
	}

}
