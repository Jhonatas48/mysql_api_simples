package api.exception;

public class FilterIsNullException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7248009432241181234L;
	static Throwable t = new Throwable("FilterIsNullException");
	 public FilterIsNullException(){
		 
		  super("The empty field is not permited");
	    }
}