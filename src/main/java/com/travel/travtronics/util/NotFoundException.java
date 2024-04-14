package com.travel.travtronics.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	public NotFoundException(String entityId, Class c) {
		super(String.format("The entity %s could not be found with the ID: %s", c.getSimpleName(), entityId));
	}

}
