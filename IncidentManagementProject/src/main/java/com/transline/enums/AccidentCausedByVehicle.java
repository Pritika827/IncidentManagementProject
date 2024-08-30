package com.transline.enums;

public enum AccidentCausedByVehicle {
	DTC("DTC"), 
	OTHERS("Others");

	private final String description;

	AccidentCausedByVehicle(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
