package com.transline.dtos;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.transline.entities.Incidents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InspectionReportDto {
	private Integer id;
	
	private String incidentId;
	private String accidentDescription;
	private boolean isPresent;
	private LocalTime timeTakenToReach;
	private String incidentReportedBy;
	private LocalTime timeToIncidentReport;
	private String reasonOfAccident;
	private boolean isBeaked;
	private String roadLightCondition;
	private float distanceToBusStop;
	private String culprit;
	private String busPartDamaged;
	private String remarks;
	private LocalDateTime workShopDateTime;
	private String partsRepaired;
	private float repairingCost;
	private float partCost;
	private float labourerCost;
	private float otherCost;
	private String jobCartNo;
	private LocalDateTime repairingDate;

}
