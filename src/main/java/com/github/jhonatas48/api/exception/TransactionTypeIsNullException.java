package com.github.jhonatas48.api.exception;

public class TransactionTypeIsNullException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7039437499344587129L;
	static Throwable t = new Throwable("TransactionTypeIsNullException");
	 
	 public TransactionTypeIsNullException(){
		 
		  super("Define the type of transaction using method .insert(),.update(),select()");
	    }

}
