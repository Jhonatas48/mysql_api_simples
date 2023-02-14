package api.interfaces;

public interface IInsert  extends ISQLParameters<IInsert>{

	public IInsert addColumn(String column,String value);
	public boolean commit();
	
}
