package com.transline.dtos;

import lombok.Data;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class PoliceRemarksDto {
	private Integer id;
//	private Incidents incident;
	private String incidentId;
	private String name;
	private String location;
	private String policeStationName;
	private Boolean isIncidentSeen;
	private String description;
	private InvestigationReportDTO investigationReport;
	private String drName;
	private String drAddress;
	private String hospitalName;
	private String referenceNo;

	@Data
	public static class InvestigationReportDTO {
		private String reportName;
		private String address;
		private String injuredDescription;
		private String remarks;

	}
}
