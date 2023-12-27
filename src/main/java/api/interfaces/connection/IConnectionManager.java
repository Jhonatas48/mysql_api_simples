package api.interfaces.connection;

import java.sql.Connection;
import java.util.List;

import api.models.enums.ConnectionType;

public interface IConnectionManager {

	public String getName();
	public void addConnection(IConnection<?> connectionWithUniqueName);
	public void removeConnection(String connectionTypeName);
	public ConnectionType getConnectionType();
	public Connection getLogConnecton();
	public Connection getConnection();
	public IConnection<?> getConnectionByName(String name);
	public void closeConnection();
	public List<String>getTables();
	
}
