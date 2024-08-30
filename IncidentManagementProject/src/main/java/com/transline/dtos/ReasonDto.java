package com.transline.dtos;

import com.transline.enums.AccidentCausedByPassenger;
import com.transline.enums.AccidentCausedByVehicle;
import com.transline.enums.AnalysisOfAccident;

import lombok.Data;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReasonDto {
	private Integer id;
	private String incidentId;
	private String busDirection;
	private String otherVehicleDirection;
	private Integer busSpeed;
	private Integer otherVehicleSpeed;
	private Boolean isHorned;
	private Boolean isBeaked;
	private String atmosphericDesc;
	private String roadCondition;
	private String roadWidth;
	private BusLightDTO busLight;
	private String vehicleLightDesc1;
	private String vehicleLightDesc2;
	private String roadLightCondition;
	private Boolean isVehicleInLane;
	private String vehicleLaneDesc;
	private String busStopDistance;
	private String trafficSignalDistance;
	private Integer busSpeedAccordingGPS;

	private AnalysisOfAccident analysisOfAccident;
    private AccidentCausedByVehicle accidentCausedByVehicle;
    private AccidentCausedByPassenger accidentCausedByPassenger;
    
	@Data
	public static class BusLightDTO {
		private Boolean headLight;
		private Boolean tailLight;
		private Boolean interiorLight;
		private Boolean brakeLight;

	}
}
