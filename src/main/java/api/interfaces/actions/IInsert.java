package api.interfaces.actions;

import java.util.function.Consumer;

import api.connection.ConnectionManager;
import api.interfaces.ISQLParameters;

public interface IInsert  extends ISQLParameters<IInsert>{

	public IInsert addColumn(String column,String value);
	public boolean commit();
	public boolean commit(Consumer<? super Throwable> failure);
	public IInsert setConnectionManager(ConnectionManager connection);
	
}
