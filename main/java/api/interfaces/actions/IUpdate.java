package api.interfaces.actions;

import api.connection.ConnectionManager;
import api.interfaces.ICommitAction;
import api.interfaces.ISQLParameters;

public interface IUpdate extends ISQLParameters<IUpdate>{

	public ICommitAction filter(String filter);
	public IUpdate addColumn(String column,String value);
	public IUpdate setConnectionManager(ConnectionManager connection);
	
}
