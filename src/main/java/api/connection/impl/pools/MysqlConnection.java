package api.connection.impl.pools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import api.connection.impl.atributes.TCPConnectionAtributes;
import api.interfaces.connection.IConnection;
import api.models.enums.ConnectionType;
import api.models.utils.Checkers;

public class MysqlConnection extends TCPConnectionAtributes implements IConnection<TCPConnectionAtributes>{

	private String name;
	public MysqlConnection(String name) {
		Checkers.validateStringNotNull(name, "name");
		this.name = name;
	}
	private void initializeVariables() {
		
		if(Checkers.isEmpty(getAdress())) {
			setAdress("127.0.0.1");
		}
		
		if(getPort() ==0) {
			setPort(3306);
		}
		
		if(Checkers.isEmpty(getPassword())) {
			setPassword("");
		}
		
	}
	@Override
	public String getAtributesConnection() {
	
		this.initializeVariables();
		return "jdbc:mysql"+getAtributesConnectionTCP();
	}
	@Override
	public Connection openConnection() {
		
		this.initializeVariables();
		if(Checkers.isEmpty(getAdress())) {
			
			throw new NullPointerException("Database is null");
		}
		
		Connection connection=null;
			try {
				connection = DriverManager.getConnection(getAtributesConnection(),getUsername(),getPassword());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				e.printStackTrace();
				return null;
			}
			
	
		return connection;
	}

	@Override
	public void closeConnection(Connection connection) {
		
		if(connection != null) {
			
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Error closing connection with MYSQL");
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
				
				e.printStackTrace();
			}
		}
	}
	@Override
	public TCPConnectionAtributes getAtributes() {
		
		return this;
	}
	@Override
	public ConnectionType getConnectionType() {
	
		return ConnectionType.MYSQL;
	}
	@Override
	public void syncronize() {
		
		
	}
	@Override
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
