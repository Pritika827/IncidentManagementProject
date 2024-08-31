package com.transline.services;

import java.util.List;

import com.transline.dtos.FurtherRemarksDto;
import com.transline.dtos.InspectionReportDto;

public interface FurtherRemarksService {

	FurtherRemarksDto saveFurtherRemarks(FurtherRemarksDto remarksDto);

	List<FurtherRemarksDto> getAllFurtherRemarks();

	FurtherRemarksDto getFurtherRemarksById(Integer id);

	FurtherRemarksDto updateFurtherRemarksDto(FurtherRemarksDto remarksDto, Integer id);

	void deleteFurtherRemarks(Integer id);

//	List<FurtherRemarksDto> getAllFutherRemarksByIncidentId(String remarksRepository);
	
	FurtherRemarksDto getFutherRemarkByIncidentId(String incidentId);
}
