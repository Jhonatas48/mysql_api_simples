package com.github.jhonatas48.api.exception;

public class ConnectionNotEstablishedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -649667016612518199L;
	 static Throwable t = new Throwable("ConnectionNotEstablishedException");
	 
	 public ConnectionNotEstablishedException(){
		 
		  super("The connection database is not established");
	    }

	  public ConnectionNotEstablishedException(String msg){
		 
		  super(msg,t);
	    }

	   
	    public ConnectionNotEstablishedException(String msg, Throwable cause){
	    	
	       // super(msg, t);
	    }

}
