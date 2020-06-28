package com.pmrendszer.controller.api.error;

public class EntityNotFoundException extends RuntimeException {
	public EntityNotFoundException() {
		super();
	}
	
	public EntityNotFoundException(String message) {
		super(message);
	}
}