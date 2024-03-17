package api.interfaces.actions;

import api.connection.ConnectionManager;
import api.interfaces.ICommitAction;
import api.interfaces.ISQLParameters;
import api.interfaces.connection.IConnection;
import api.interfaces.createatrrubutes.ICreateAtributes;

public interface ICreate  extends ISQLParameters<ICreate>,ICommitAction{

	public ICreate addColumn(String column,String value);
	public ICreate addColumn(String column,String value,ICreateAtributes<?> createAtribute);
	public ICreate setConnection(IConnection<?> connection);
	public ICreate setConnectionManager(ConnectionManager connectionManager);

}
