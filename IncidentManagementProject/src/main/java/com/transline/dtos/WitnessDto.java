package com.transline.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.transline.entities.Witness.OtherReference;

import lombok.Data;

@Data
public class WitnessDto {

	private Integer id;
	private String incidentId;
	private String name;
	private String address;
	private Boolean isInBus;
	private String seatDirection; // Applicable if isInBus is true
	private Boolean seenIncident;
	private Integer busSpeed;
	private Boolean isHorned;
	private Boolean isBeaked;
	private Boolean tryToStopIncident;
	private String culprit;
	private List<OtherReferenceDTO> anyOtherRef; // List of references

	private String description;
	private LocalDate date;
	private LocalTime time;

	@Data
	public static class OtherReferenceDTO {
		private String refName;
		private String refAddress;
	}
}
