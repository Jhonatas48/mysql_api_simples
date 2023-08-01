package api.exception.connection;

public class DuplicateConnectionNameException extends Exception {


	private static final long serialVersionUID = 2923505737187084628L;
	static Throwable t = new Throwable("DuplicateConnectionNameException");
	 
	 public DuplicateConnectionNameException(){
		 
		  super("The connection name already exists");
	    }

	  public DuplicateConnectionNameException(String msg){
		 
		  super(msg,t);
	    }

	   
	    public DuplicateConnectionNameException(String msg, Throwable cause){
	    	
	       // super(msg, t);
	    }

}

