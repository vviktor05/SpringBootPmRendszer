 package com.pmrendszer.service;

import java.util.List;
import com.pmrendszer.controller.api.error.EntityNotFoundException;

public abstract class CheckerClass {
	public static final String NAME_REGEX = "^[a-zA-Z áéíóöőúüűÁÉÍÓÖŐÚÜŰ]+$";
	public static final String DATE_REGEX = "[1-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]";
	public static final String DATE_TIME_REGEX = "[1-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9] (\\d{2}):(\\d{2}):(\\d{2})";
	public static final String NUMBER_REGEX = "^[\\d]*$";

	public static void ifEmptyThrowException(Object obj) throws EntityNotFoundException {
		if (obj == null) {
			throw new EntityNotFoundException("Nincs találat!");
		}
	}

	public static void ifEmptyThrowException(List list) throws EntityNotFoundException {
		if (list == null || list.isEmpty()) {
			throw new EntityNotFoundException("Nincs találat!");
		}
	}

	public static boolean isValidMinLength(String text, int minLength) {
		return text.trim().length() >= minLength && text != null;
	}

	public static boolean isValidName(String name) {
		return name.matches(NAME_REGEX);
	}
	
	public static boolean isValidDate(String date) {
		return date.matches(DATE_REGEX);
	}
	
	public static boolean isValidDateTime(String dateTime) {
		return dateTime.matches(DATE_TIME_REGEX);
	}
	
	public static boolean isValidPhoneNumber(String phoneNumber) {
		return phoneNumber.matches(NUMBER_REGEX);
	}
}