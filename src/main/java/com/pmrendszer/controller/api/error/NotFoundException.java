package com.pmrendszer.controller.api.error;

public class NotFoundException extends RuntimeException {
	public NotFoundException() {
		super("Nincs találat");
	}
}