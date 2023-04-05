package api.connection.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import api.connection.IConnection;
import api.models.enums.ConnectionType;
import api.models.utils.Checkers;

public class SqliteConnection extends ConnectionAtributesFiles implements IConnection<ConnectionAtributesFiles>{
	
	public SqliteConnection(String nameFile) {
		Checkers.validateStringNotNull(nameFile, "nameFile");
		String[] nameWiExtension = nameFile.split("\\.");
		
		if(nameWiExtension.length < 2) {
			this.setNameFile(nameFile+".db");
			return;
		}
		this.setNameFile(nameWiExtension[0]+".db");
	}
	
	@Override
	public String getAtributesConnection() {
	
		return "jdbc:sqlite"+getAtributesConnectionFiles();
	}
	@Override
	public Connection openConnection() {
		
		Connection connection=null;
		
		try {
			connection = DriverManager.getConnection(getAtributesConnection());
			
		} catch (SQLException e) {
			System.out.println("Erro ao abrir conexao com o SQLITE");
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public void closeConnection(Connection connection) {
		
		if(connection != null) {
			
			try {
				connection.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}

	@Override
	public void closeResultSet(ResultSet resultSet) {
	
		if(resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				System.out.println("Erro ao abrir conexao com o SQLITE");
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public ConnectionAtributesFiles getAtributes() {
	
		return this;
	}

	@Override
	public ConnectionType geConnectionType() {
		
		return ConnectionType.SQLITE;
	}

	@Override
	public void syncronize() {
		// TODO Auto-generated method stub
		
	}

}
