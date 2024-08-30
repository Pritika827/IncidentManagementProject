package com.transline.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class FurtherRemarksDto {

	private Integer id;
	private String incidentId;
	private String reportedBy;
	private String description;
	private LocalDate date;
	private LocalTime time;
}