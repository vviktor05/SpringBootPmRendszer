package com.pmrendszer.service;

import java.util.List;

import com.pmrendszer.controller.api.error.EntityNotFoundException;

public abstract class CheckerClass {
	public static void notEmptyOrThrow(Object obj) throws EntityNotFoundException{
		if(obj == null) {
			throw new EntityNotFoundException();
		}
	}
	
	public static void notEmptyOrThrow(List list) throws EntityNotFoundException{
		if(list == null || list.isEmpty()) {
			throw new EntityNotFoundException();
		}
	}
}