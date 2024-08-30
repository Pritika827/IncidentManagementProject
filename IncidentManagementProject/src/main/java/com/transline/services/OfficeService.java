package com.transline.services;

import java.util.List;

import com.transline.dtos.OfficeDto;
import com.transline.entities.Office;

public interface OfficeService {

//	IncidentsDto saveIncidents(IncidentsDto incidentsDto);
//
//	List<IncidentsDto> getAllIncidentsDetails();
//
//	IncidentsDto getIncidentById(String incidentId);
//
//	IncidentsDto updateIncidentsDto(IncidentsDto incidentsDto, String incidentId);
//
//	void deteleIncidents(String incidentsId);
	
	OfficeDto saveOffice(OfficeDto officeDto);
	
	List<OfficeDto> getAllOfficeDetails();
	
	OfficeDto getOfficeById(String offCd);
	
	Office updateOfficeDto(OfficeDto officeDto, String offCd);
	
	void deleteOffice(String offCd);

}
