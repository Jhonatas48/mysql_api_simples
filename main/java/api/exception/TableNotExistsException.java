package api.exception;

public class TableNotExistsException extends RuntimeException {

	private static final long serialVersionUID = 2983258050393375928L;
	static Throwable t = new Throwable("TableNotExistsException");
	 public TableNotExistsException(){
		 
		  super("Table Not Exists");
	    }
	 public TableNotExistsException(String message) {
		 super(message);
	 }
}