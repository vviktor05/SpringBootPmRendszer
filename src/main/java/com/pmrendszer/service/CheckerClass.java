package com.pmrendszer.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import com.pmrendszer.controller.api.error.EntityNotFoundException;

public abstract class CheckerClass {
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final String NAME_REGEX = "^[a-zA-Z áéíóöőúüűÁÉÍÓÖŐÚÜŰ]+$";
	public static final String DATE_REGEX = "[1-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]";
	public static final String DATE_TIME_REGEX = "[1-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9] (\\d{2}):(\\d{2}):(\\d{2})";
	public static final String NUMBER_REGEX = "^[\\d]*$";

	public static void ifEmptyThrowException(Object obj) throws EntityNotFoundException {
		if (obj == null) {
			throw new EntityNotFoundException("Nincs találat!");
		}
	}

	public static void ifEmptyThrowException(List<?> list) throws EntityNotFoundException {
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

	public static boolean isValidDates(LocalDate date1, LocalDate date2) {
		return date1.isBefore(date2);
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

	public static Date parseDate(String date, SimpleDateFormat simpleDateFormat) {
		try {
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
}