package com.transline.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

	String resourceName;
	String fieldName;
	String fieldValue;
	Long fieldValueInt;
	
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValueInt) {
		super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValueInt));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValueInt = fieldValueInt;
	}
	public ResourceNotFoundException(String resourceName, String fieldName, String incidentId) {
		super(String.format("%s not found with %s: %s", resourceName, fieldName, incidentId));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = incidentId;
	}

	public ResourceNotFoundException() {
		super("Resource not found on server");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
