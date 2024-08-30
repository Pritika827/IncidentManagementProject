package com.transline.entities;

import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Inspection_report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InspectionReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "incident_id",nullable = false)
	private Incidents incident;

	@Column(length = 1000)
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