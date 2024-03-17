package api.exception;

public class TableColumnNotExistsException extends RuntimeException {

	private static final long serialVersionUID = 2983258050393375928L;
	static Throwable t = new Throwable("TableColumnExistsException");
	 public TableColumnNotExistsException(){
		 
		  super("Table Column Not Exists");
	    }
	 public TableColumnNotExistsException(String message) {
		 super(message);
	 }
}