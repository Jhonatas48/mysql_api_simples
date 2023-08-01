package api.interfaces;

import api.connection.ConnectionManager;


public interface IDelete extends ISQLParameters<IDelete> {

	public ICommitAction filter(String filter);
	public IDelete setConnectionManager(ConnectionManager connection);
	
}
