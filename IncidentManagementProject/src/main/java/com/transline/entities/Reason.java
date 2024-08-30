package com.transline.entities;

import com.transline.enums.AccidentCausedByPassenger;
import com.transline.enums.AccidentCausedByVehicle;
import com.transline.enums.AnalysisOfAccident;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "reasons")
@Data
public class Reason {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

//    @Column(nullable = false)
//    private String incidentId;

	@OneToOne
	@JoinColumn(name = "incident_id", nullable = false)
	private Incidents incident;

	private String busDirection;
	private String otherVehicleDirection;
	private Integer busSpeed;
	private Integer otherVehicleSpeed;
	private Boolean isHorned;
	private Boolean isBeaked;
	private String atmosphericDesc;
	private String roadCondition;
	private String roadWidth;

	@Embedded
	private BusLight busLight;

	private String vehicleLightDesc1;
	private String vehicleLightDesc2;
	private String roadLightCondition;
	private Boolean isVehicleInLane;
	private String vehicleLaneDesc;
	private String busStopDistance;
	private String trafficSignalDistance;
	private Integer busSpeedAccordingGPS;
	
	@Enumerated(EnumType.STRING)
    private AnalysisOfAccident analysisOfAccident;

    @Enumerated(EnumType.STRING)
    private AccidentCausedByVehicle accidentCausedByVehicle;

    @Enumerated(EnumType.STRING)
    private AccidentCausedByPassenger accidentCausedByPassenger;

	@Data
	@Embeddable
	public static class BusLight {
		private Boolean headLight;
		private Boolean tailLight;
		private Boolean interiorLight;
		private Boolean brakeLight;

	}
}
