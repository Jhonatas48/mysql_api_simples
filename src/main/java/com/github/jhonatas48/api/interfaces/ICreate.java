package com.github.jhonatas48.api.interfaces;

import com.github.jhonatas48.api.connection.IConnection;

public interface ICreate  extends ISQLParameters<ICreate>,ICommitAction{

	public ICreate addColumn(String column,String value);
	public ICreate addColumn(String column,String value,ICreateAtributes<?> createAtribute);
	public ICreate setConnection(IConnection<?> connection);

}
