package com.github.jhonatas48.api.connection;

import java.sql.Connection;
import java.sql.ResultSet;

import com.github.jhonatas48.api.models.enums.ConnectionType;

public interface IConnection<T extends IConnectionAtributes> {

	public Connection openConnection();

	public void closeConnection(Connection connection);

	public T getAtributes();

	public void closeResultSet(ResultSet resultSet);

	public ConnectionType geConnectionType();
	
	public void syncronize();
}
