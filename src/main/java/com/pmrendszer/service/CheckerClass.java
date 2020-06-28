package com.pmrendszer.service;

import java.util.List;

import com.pmrendszer.controller.api.error.NotFoundException;

public abstract class CheckerClass {
	public static void notEmptyOrThrow(Object obj) throws NotFoundException{
		if(obj == null) {
			throw new NotFoundException();
		}
	}
	
	public static void notEmptyOrThrow(List list) throws NotFoundException{
		if(list == null || list.isEmpty()) {
			throw new NotFoundException();
		}
	}
}