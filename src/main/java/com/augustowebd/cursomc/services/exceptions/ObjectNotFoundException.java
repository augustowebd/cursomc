package com.augustowebd.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String eMessage) {
		super(eMessage);
	}
	
	public ObjectNotFoundException(String eMessage, Throwable cause) {
		super(eMessage, cause);
	}
}
