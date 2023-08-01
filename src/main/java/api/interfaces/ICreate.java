package api.interfaces;

import api.connection.ConnectionManager;
import api.connection.IConnection;

public interface ICreate  extends ISQLParameters<ICreate>,ICommitAction{

	public ICreate addColumn(String column,String value);
	public ICreate addColumn(String column,String value,ICreateAtributes<?> createAtribute);
	public ICreate setConnection(IConnection<?> connection);
	public ICreate setConnectionManager(ConnectionManager connectionManager);

}
