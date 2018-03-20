package com.capco.exception;

public class VTRException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorMessage;
	 
	public String getErrorMessage() {
		return errorMessage;
	}
	public VTRException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public VTRException() {
		super();
	}

}
