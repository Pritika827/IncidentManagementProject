package com.transline.enums;

public enum AccidentCausedByPassenger {
	FRONT_GATE("Front Gate"), 
	REAR_GATE("Rear Gate");

	private final String description;

	AccidentCausedByPassenger(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
