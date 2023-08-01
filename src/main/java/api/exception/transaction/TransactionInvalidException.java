/**
 * 
 */
package api.exception.transaction;

/**
 * @author Cristiano TI
 *
 */
public class TransactionInvalidException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6904062333413910819L;
	static Throwable t = new Throwable("FilterIsNullException");
	/**
	 * 
	 */
	public TransactionInvalidException() {
		 super("The Transaction Type is not allowed");
	}

	/**
	 * @param message
	 */
	public TransactionInvalidException(String message) {
		super(message);
	
	}

	/**
	 * @param cause
	 */
	public TransactionInvalidException(Throwable cause) {
		super(t);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public TransactionInvalidException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}


}
