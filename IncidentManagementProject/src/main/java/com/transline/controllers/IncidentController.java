package com.transline.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transline.dtos.IncidentsDto;
import com.transline.dtos.InspectionReportDto;
import com.transline.services.IncidentServices;
import com.transline.services.WitnessAndOtherService;
import com.transline.servicesImp.InspectionReportServiceImpl;
import com.transline.utils.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/incidents")
@CrossOrigin(origins = "*",allowedHeaders = "*",allowCredentials = "false")
@Tag(name = "Incidents Management", description = "Operations related to incidents management")
public class IncidentController {

	@Autowired
	private IncidentServices incidentServices;
	
	@Autowired
	private WitnessAndOtherService witnessService;

	@PostMapping
	@Operation(summary = "Create incident", description = "Add a new incidents to the system")
	public ResponseEntity<IncidentsDto> createNewIncident(@RequestBody IncidentsDto incidentsDto) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(incidentServices.saveIncidents(incidentsDto));
	}

	@GetMapping("/{incidentId}")
	@Operation(summary = "Get incident by ID", description = "Retrieve a incident by their ID")
	public ResponseEntity<IncidentsDto> getIncidentById(@PathVariable String incidentId) {
		return ResponseEntity.ok(this.incidentServices.getIncidentById(incidentId));
	}

	@GetMapping
	@Operation(summary = "Get all incidents", description = "Retrieve a list of all incidents")
	public ResponseEntity<List<IncidentsDto>> getAllIncidentsDetails() {
		return ResponseEntity.ok(this.incidentServices.getAllIncidentsDetails());
	}
	
	@GetMapping("/incident-ids")
	@Operation(summary = "Get all incident ids", description = "Retrieve a list of all incidents id")
	public ResponseEntity<List<String>> getAllIncidentIds() {
		return ResponseEntity.ok(this.incidentServices.getAllIncidentIds());
	}

	@PutMapping("/{incidentId}")
	@Operation(summary = "Update incident", description = "Update an existing incident's details by their ID")
	public ResponseEntity<IncidentsDto> updateIncident(@RequestBody IncidentsDto incidentsDto,
			@PathVariable String incidentId) {
		IncidentsDto updatedIncidents = this.incidentServices.updateIncidentsDto(incidentsDto, incidentId);
		return ResponseEntity.ok(updatedIncidents);
	}

//	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{incidentId}")
	@Operation(summary = "Delete incident", description = "Delete a incident by their ID")
	public ResponseEntity<ApiResponse> deleteInteger(@PathVariable String incidentId) {
		this.incidentServices.deteleIncidents(incidentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("incident deleted successfully", true), HttpStatus.OK);
	}
	
	//----------------------------------Inspection Report------------------------------------------------------
//	@Autowired
//	private InspectionReportServiceImpl reportService;
//
//	@PostMapping("/inspectionReport")
//	@Operation(summary = "Create Inspection Report", description = "Add a new Inspection Report to the system")
//	public ResponseEntity<InspectionReportDto> createNewInspectionReport(@RequestBody InspectionReportDto reportDto) {
//		return ResponseEntity.status(HttpStatus.CREATED).body(reportService.saveInspectionReport(reportDto));
//	}
//	
//	@GetMapping("/inspectionReport")
//	@Operation(summary = "Get all inspection reports", description = "Retrieve a list of all inspection reports")
//	public ResponseEntity<List<InspectionReportDto>> getAllInspectionReport() {
//		return ResponseEntity.ok(this.reportService.getAllInspectionReportDetails());
//	}
//	
//	@GetMapping("/inspectionReport/{id}")
//	@Operation(summary = "Get inspection report by ID", description = "Retrieve a inspection report by their ID")
//	public ResponseEntity<InspectionReportDto> getInspectionReportById(@PathVariable Integer id) {
//		return ResponseEntity.ok(this.reportService.getInspectionReportById(id));
//	}
//
//
//	@PutMapping("/inspectionReport/{id}/{incidentId}")
//	@Operation(summary = "Update inspection report", description = "Update an existing inspection report's details by their ID")
//	public ResponseEntity<InspectionReportDto> updateInspectionReport(
//			@RequestBody InspectionReportDto inspectionReportDto, @PathVariable Integer id,@PathVariable String incidentId) {
//		inspectionReportDto.setId(id);
//		inspectionReportDto.setIncidentId(incidentId);
//		InspectionReportDto updatedInspectionReport = this.reportService.updateInspectionReportDto(inspectionReportDto, id,incidentId);
//		  System.out.print("service"+id); 
//		return ResponseEntity.ok(updatedInspectionReport);
//	}
//
//	@DeleteMapping("/inspectionReport/{id}")
//	@Operation(summary = "Delete inspection report", description = "Delete a inspection report by their ID")
//	public ResponseEntity<ApiResponse> deleteInteger(@PathVariable Integer id) {
//		this.reportService.deleteInspectionReport(id);
//		return new ResponseEntity<ApiResponse>(new ApiResponse("inspection report deleted successfully", true),
//				HttpStatus.OK);
//	}
//--------------------------------------------------------------------------------------------------------------------
}
