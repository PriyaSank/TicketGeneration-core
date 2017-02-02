package com.ticket.exception;



public class PersistenceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersistenceException(String reason, Throwable cause) {
		super(reason, cause);
		
	}

	public PersistenceException(String reason) {
		super(reason);
		
	}



}
