package api.exception;

public class DuplicatePrimaryKeyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6874315925708241093L;

	public DuplicatePrimaryKeyException(){
		 
		super("The empty field is not permited");
	}

}
