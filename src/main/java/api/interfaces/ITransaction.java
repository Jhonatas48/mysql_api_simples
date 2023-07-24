package api.interfaces;

public interface ITransaction {

	public ICreate create();
	
	public IInsert insert();
	
	public ISelect select();
	
	public IUpdate update();
	
	public IDelete delete();
	
	public Exception getException();
}
