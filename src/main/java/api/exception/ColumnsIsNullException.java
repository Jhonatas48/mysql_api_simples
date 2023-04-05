package api.exception;

public class ColumnsIsNullException  extends RuntimeException{

	/**
	 * Feito por Jhonatas Tomaz
	 */
	private static final long serialVersionUID = 3701828533580646326L;
	
	static Throwable t = new Throwable("ColumnsIsNullException");
	 
	 public ColumnsIsNullException (){
		 
		  super("Define the columns");
	    }
	 
	 public  ColumnsIsNullException(String message) {
			super(message);
		
		}

}