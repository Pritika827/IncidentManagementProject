package com.transline.services;

import java.util.List;

import com.transline.dtos.InspectionReportDto;
import com.transline.dtos.InsuranceDto;

public interface InspectionReportService {

	InspectionReportDto saveInspectionReport(InspectionReportDto inspectionReportDto);

	List<InspectionReportDto> getAllInspectionReportDetails();

	InspectionReportDto getInspectionReportById(Integer id);

	InspectionReportDto updateInspectionReportDto(InspectionReportDto inspectionReportDto, Integer id);

	void deleteInspectionReport(Integer id);
	
	InspectionReportDto getInspectionByIncidentId(String incidentId);
}
