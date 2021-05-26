package com.pmrendszer.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.pmrendszer.controller.api.error.EntityNotFoundException;

public class CheckerClassTest {

	@Test
	public void ifEmptyThrowException_doesNotThrow() {
		assertDoesNotThrow(() -> CheckerClass.ifEmptyThrowException(new Object()));
		
		ArrayList<Object> list = new ArrayList<>();
		list.add(new Object());
		
		assertDoesNotThrow(() -> CheckerClass.ifEmptyThrowException(list));
	}
	
	
	@Test
	public void ifEmptyThrowException_throwEntityNotFoundException() {
		assertThrows(EntityNotFoundException.class, () -> CheckerClass.ifEmptyThrowException(null));
		
		assertThrows(EntityNotFoundException.class, () -> CheckerClass.ifEmptyThrowException(new ArrayList<>()));
		
		assertThrows(EntityNotFoundException.class, () -> CheckerClass.ifEmptyThrowException(null));
	}
	
	@Test
	public void isValidMinLength_thenReturnTrue() {
		assertTrue(CheckerClass.isValidMinLength("Teszt", 3));
		assertTrue(CheckerClass.isValidMinLength("Teszt", 5));
		assertTrue(CheckerClass.isValidMinLength("T e s z t", 5));
	}
	
	@Test
	public void isValidMinLength_thenReturnFalse() {
		assertFalse(CheckerClass.isValidMinLength("Teszt", 6));
		assertFalse(CheckerClass.isValidMinLength("T e s z t", 6));
		assertFalse(CheckerClass.isValidMinLength(null, 6));
	}
	
	@Test
	public void isValidName_thenReturnTrue() {
		assertTrue(CheckerClass.isValidName("Teszt Ernő"));
		assertTrue(CheckerClass.isValidName("Teszt"));
	}
	
	@Test
	public void isValidName_thenReturnFalse() {
		assertFalse(CheckerClass.isValidName("Teszt%Ernő"));
		assertFalse(CheckerClass.isValidName("Teszt .Ernő"));
	}
	
	@Test
	public void isValidDates_thenReturnTrue() {
		assertTrue(CheckerClass.isValidDates(LocalDate.of(2021, 05, 01), LocalDate.of(2021, 05, 02)));
		assertTrue(CheckerClass.isValidDates(LocalDate.of(2021, 05, 01), LocalDate.of(2021, 06, 01)));
	}
	
	@Test
	public void isValidDates_thenReturnFalse() {
		assertFalse(CheckerClass.isValidDates(LocalDate.of(2021, 05, 01), LocalDate.of(2021, 04, 30)));
		assertFalse(CheckerClass.isValidDates(LocalDate.of(2021, 05, 01), LocalDate.of(2021, 05, 01)));
	}
}
