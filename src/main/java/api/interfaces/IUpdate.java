package api.interfaces;

public interface IUpdate extends ISQLParameters<IUpdate>{

	public ICommitAction filter(String filter);
	public IUpdate addColumn(String column,String value);
	
}
