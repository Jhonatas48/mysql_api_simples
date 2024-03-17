package api.interfaces.createatrrubutes;

public interface IUnique extends ICreateAtributes<IUnique> {

	public IUnique addColumn(String column);
	public String getColumns();
	public int getColumnsQuantity();
	
}
