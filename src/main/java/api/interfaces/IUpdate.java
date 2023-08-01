package api.interfaces;

import api.connection.ConnectionManager;

public interface IUpdate extends ISQLParameters<IUpdate>{

	public ICommitAction filter(String filter);
	public IUpdate addColumn(String column,String value);
	public IUpdate setConnectionManager(ConnectionManager connection);
	
}
