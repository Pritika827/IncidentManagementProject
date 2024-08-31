package com.transline.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transline.dtos.CombinedResponseDto;
import com.transline.dtos.FurtherRemarksDto;
import com.transline.dtos.IncidentsDto;
import com.transline.dtos.InspectionReportDto;
import com.transline.dtos.InsuranceDto;
import com.transline.dtos.PoliceRemarksDto;
import com.transline.dtos.ReasonDto;
import com.transline.dtos.WitnessAndOtherDto;
import com.transline.exceptions.ResourceNotFoundException;
import com.transline.services.FurtherRemarksService;
import com.transline.services.IncidentServices;
import com.transline.services.InspectionReportService;
import com.transline.services.InsuranceService;
import com.transline.services.PoliceRemarksService;
import com.transline.services.ReasonService;
import com.transline.services.WitnessAndOtherService;

import ch.qos.logback.classic.Logger;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/combinedResponse")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "false")
public class CombinedResponseController {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(CombinedResponseController.class);

	@Autowired
	private IncidentServices incidentServices;

	@Autowired
	private ReasonService reasonService;

	@Autowired
	private WitnessAndOtherService witnessAndOtherService;

	@Autowired
	private PoliceRemarksService policeRemarksService;

	@Autowired
	private InsuranceService insuranceService;

	@Autowired
	private InspectionReportService reportService;

	@Autowired
	private FurtherRemarksService remarksService;

	@GetMapping("{incidentId}")
	@Operation(summary = "Get all incidents, reasons, witnesses, police remarks, insurance, inspection reports, and further remarks", description = "Retrieve a list of all incidents, reasons, witnesses, police remarks, insurance, inspection reports, and further remarks by incident ID")
	public ResponseEntity<CombinedResponseDto> getAllDataById(@PathVariable String incidentId) {
		IncidentsDto incidentsDtos = null;
		ReasonDto reasonDtos = null;
		WitnessAndOtherDto witnessAndOtherDto = null;
		PoliceRemarksDto policeRemarksDto = null;
		InsuranceDto insuranceDto = null;
		InspectionReportDto inspectionReportDto = null;
		FurtherRemarksDto furtherRemarksDto = null;
		CombinedResponseDto combinedDto = new CombinedResponseDto();

		try {
			incidentsDtos = incidentServices.getIncidentById(incidentId);
		} catch (ResourceNotFoundException e) {
			logger.info("Incident with ID {} not found.", incidentId);
		}

		try {
			reasonDtos = reasonService.getReasonByIncidentId(incidentId);
		} catch (ResourceNotFoundException e) {
			logger.info("Reason with incident ID {} not found.", incidentId);
		}

		try {
			witnessAndOtherDto = witnessAndOtherService.getWitnessByIncidentId(incidentId);
		} catch (ResourceNotFoundException e) {
			logger.info("Reason with incident ID {} not found.", incidentId);
		}

		try {
			policeRemarksDto = policeRemarksService.getPolicaRemarkByIncidentId(incidentId);
		} catch (ResourceNotFoundException e) {
			logger.info("Police remarks with incident ID {} not found.", incidentId);
		}

		try {
			insuranceDto = insuranceService.getInsuranceByIncidentId(incidentId);
		} catch (ResourceNotFoundException e) {
			logger.info("Insurance data with incident ID {} not found.", incidentId);
		}

		try {
			inspectionReportDto = reportService.getInspectionByIncidentId(incidentId);
		} catch (ResourceNotFoundException e) {
			logger.info("Inspection report with incident ID {} not found.", incidentId);
		}

		try {
			furtherRemarksDto = remarksService.getFutherRemarkByIncidentId(incidentId);
		} catch (ResourceNotFoundException e) {
			logger.warn("Further remarks with incident ID {} not found.", incidentId);
		}

		if (incidentsDtos != null) {
			combinedDto.setIncidents(incidentsDtos);
		}

		if (reasonDtos != null) {
			combinedDto.setReasons(reasonDtos);
		}

		if (witnessAndOtherDto != null) {
			combinedDto.setWitnessAndOther(witnessAndOtherDto);
		}

		if (policeRemarksDto != null) {
			combinedDto.setPoliceRemarks(policeRemarksDto);
		}

		if (insuranceDto != null) {
			combinedDto.setInsurance(insuranceDto);
		}

		if (inspectionReportDto != null) {
			combinedDto.setInspectionReport(inspectionReportDto);
		}

		if (furtherRemarksDto != null) {
			combinedDto.setFurtherRemarks(furtherRemarksDto);
		}

		if (incidentsDtos == null && reasonDtos == null && witnessAndOtherDto == null && policeRemarksDto == null
				&& insuranceDto == null && inspectionReportDto == null && furtherRemarksDto == null) {
			return ResponseEntity.notFound().build();
		}

		// Return combined data if at least one of Incident, Reason, WitnessAndOther,
		// PoliceRemarks, Insurance, InspectionReport, or FurtherRemarks is found
		return ResponseEntity.ok(combinedDto);
	}
}
