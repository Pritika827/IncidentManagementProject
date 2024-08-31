package com.transline.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transline.dtos.InspectionReportDto;
import com.transline.dtos.InsuranceDto;
import com.transline.entities.Incidents;
import com.transline.servicesImp.InspectionReportServiceImpl;
import com.transline.utils.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/inspectionReport")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "false")
@Tag(name = "Inspection Report Management", description = "Operations related to inspection report management")
public class InspectionReportController {

	@Autowired
	private InspectionReportServiceImpl reportService;

	@PostMapping
	@Operation(summary = "Create Inspection Report", description = "Add a new Inspection Report to the system")
	public ResponseEntity<InspectionReportDto> createNewInspectionReport(@RequestBody InspectionReportDto reportDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(reportService.saveInspectionReport(reportDto));
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get inspection report by ID", description = "Retrieve a inspection report by their ID")
	public ResponseEntity<InspectionReportDto> getInspectionReportById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.reportService.getInspectionReportById(id));
	}

	@GetMapping
	@Operation(summary = "Get all inspection reports", description = "Retrieve a list of all inspection reports")
	public ResponseEntity<List<InspectionReportDto>> getAllInspectionReport() {
		return ResponseEntity.ok(this.reportService.getAllInspectionReportDetails());
	}

	@GetMapping("/incident/{incidentId}")
	public ResponseEntity<InspectionReportDto> getInspectionByIncidentId(@PathVariable String incidentId) {
		InspectionReportDto inspectionReportDto = reportService.getInspectionByIncidentId(incidentId);
		return ResponseEntity.ok(inspectionReportDto);
	}

	@PutMapping("/{id}/{incidentId}")
	@Operation(summary = "Update inspection report", description = "Update an existing inspection report's details by their ID")
	public ResponseEntity<InspectionReportDto> updateInspectionReport(
			@RequestBody InspectionReportDto inspectionReportDto, @PathVariable Integer id,
			@PathVariable String incidentId) {
		inspectionReportDto.setId(id);
		inspectionReportDto.setIncidentId(incidentId);
		InspectionReportDto updatedInspectionReport = this.reportService.updateInspectionReportDto(inspectionReportDto,
				id);
		System.out.print("service" + id);
		return ResponseEntity.ok(updatedInspectionReport);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete inspection report", description = "Delete a inspection report by their ID")
	public ResponseEntity<ApiResponse> deleteInteger(@PathVariable Integer id) {
		this.reportService.deleteInspectionReport(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("inspection report deleted successfully", true),
				HttpStatus.OK);
	}
}

/*
 {
    "incidentId": "DTC202400009",    
   "accidentDescription": "A bus collided with a delivery truck due to slippery road conditions.",
  "isPresent": true,
  "timeTakenToReach": "00:45:00",
  "incidentReportedBy": "Emily Davis",
  "timeToIncidentReport": "00:30:00",
  "reasonOfAccident": "Slippery roads and reduced visibility.",
  "isBeaked": true,
  "roadLightCondition": "Poor",
  "distanceToBusStop": 300.0,
  "culprit": "Both drivers",
  "busPartDamaged": "Front and side panels",
  "remarks": "Both vehicles suffered substantial damage and were towed.",
  "workShopDateTime": "2024-08-28T08:00:00",
  "partsRepaired": "Front panel, side panels, engine",
  "repairingCost": 7500.00,
  "partCost": 4500.00,
  "labourerCost": 2000.00,
  "otherCost": 1000.00,
  "jobCartNo": "JCN9101",
  "repairingDate": "2024-08-28T17:00:00"
  }*/

