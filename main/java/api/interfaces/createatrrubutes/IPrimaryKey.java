package api.interfaces.createatrrubutes;

public interface IPrimaryKey extends ICreateAtributes<IPrimaryKey>{

	
	public IPrimaryKey addAutoIncrement();
	public IPrimaryKey setColumnName(String columnName);
	public String getColumnName();
	public String getValue();
	public void setValue(String value);
	public boolean isAutoIncrement();
	
	
}
