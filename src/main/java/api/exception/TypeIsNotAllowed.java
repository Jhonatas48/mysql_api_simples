/**
 * 
 */
package api.exception;

/**
 * @author Cristiano TI
 *
 */
public class TypeIsNotAllowed extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3656176314003132621L;

	/**
	 * 
	 */
	public TypeIsNotAllowed() {
		super("The value constains Primary Key or Foreign Key or Unique or Auto Increment use the  respective functions PrimaryKey(),ForeignKey() or Unique()");
	}

	/**
	 * @param message
	 */
	public TypeIsNotAllowed(String message) {
		super(message);
		
	}

	/**
	 * @param cause
	 */
	public TypeIsNotAllowed(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public TypeIsNotAllowed(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public TypeIsNotAllowed(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
