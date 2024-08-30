package com.transline.services;

import java.util.List;

import com.transline.dtos.InspectionReportDto;

public interface InspectionReportService {

	InspectionReportDto saveInspectionReport(InspectionReportDto inspectionReportDto);

	List<InspectionReportDto> getAllInspectionReportDetails();

	InspectionReportDto getInspectionReportById(Integer id);

	InspectionReportDto updateInspectionReportDto(InspectionReportDto inspectionReportDto, Integer id);

	void deleteInspectionReport(Integer id);
}
