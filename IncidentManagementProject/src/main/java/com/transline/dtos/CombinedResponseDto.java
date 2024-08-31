package com.transline.dtos;

import lombok.Data;

@Data
public class CombinedResponseDto {

	private IncidentsDto incidents;
	private ReasonDto reasons;
	private WitnessAndOtherDto witnessAndOther;
	private PoliceRemarksDto policeRemarks;
	private InsuranceDto insurance;
	private InspectionReportDto inspectionReport;
	private FurtherRemarksDto furtherRemarks;
}
