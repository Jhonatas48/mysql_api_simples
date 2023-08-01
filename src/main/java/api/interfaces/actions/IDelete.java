package api.interfaces.actions;

import api.connection.ConnectionManager;
import api.interfaces.ICommitAction;
import api.interfaces.ISQLParameters;


public interface IDelete extends ISQLParameters<IDelete> {

	public ICommitAction filter(String filter);
	public IDelete setConnectionManager(ConnectionManager connection);
	
}
