package com.transline.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.transline.dtos.PoliceRemarksDto;
import com.transline.dtos.WitnessAndOtherDto;
import com.transline.entities.Incidents;
import com.transline.entities.PoliceRemarks;
import com.transline.exceptions.ResourceNotFoundException;
import com.transline.repositories.IncidentRepository;
import com.transline.repositories.PoliceRemarksRepository;
import com.transline.services.PoliceRemarksService;
import com.transline.utils.ApiResponse;

@RestController
@RequestMapping("/api/police-remarks")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "false")
public class PoliceRemarksController {

	private static final Logger logger = LoggerFactory.getLogger(PoliceRemarksController.class);

	@Autowired
	private PoliceRemarksService policeRemarksService;

//	@Autowired
//	private IncidentRepository incidentRepository;
//	
//	@Autowired
//	private PoliceRemarksRepository remarksRepository;

	@PostMapping
	public ResponseEntity<PoliceRemarksDto> createPoliceRemarks(@RequestBody PoliceRemarksDto policeRemarksDto) {
		PoliceRemarksDto savedPoliceRemarksDTO = policeRemarksService.savePoliceRemarks(policeRemarksDto);
		return ResponseEntity.ok(savedPoliceRemarksDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PoliceRemarksDto> getPoliceRemarksById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.policeRemarksService.getPoliceRemarksById(id));
	}

	@GetMapping
	public ResponseEntity<List<PoliceRemarksDto>> getAllPoliceRemarks() {
		List<PoliceRemarksDto> policeRemarksList = policeRemarksService.getAllPoliceRemarks();
		return ResponseEntity.ok(policeRemarksList);
	}

	@GetMapping("/incident/{incidentId}")
	public ResponseEntity<PoliceRemarksDto> getPoliceRemarkByIncidentId(@PathVariable String incidentId) {
		PoliceRemarksDto policeRemarksDto = policeRemarksService.getPolicaRemarkByIncidentId(incidentId);
		return ResponseEntity.ok(policeRemarksDto);
	}

	// http://localhost:8080/api/police-remarks/3/DTC2024000032
	@PutMapping("/{id}/{incidentId}")
	public ResponseEntity<PoliceRemarksDto> updatePoliceRemarks(@PathVariable Integer id,
			@PathVariable String incidentId, @RequestBody PoliceRemarksDto policeRemarksDto) {

//	    Incidents incident = incidentRepository.findById(incidentId)
//	            .orElseThrow(() -> new ResourceNotFoundException("Incident", "id", incidentId));
//	    
//	    PoliceRemarks remarks=remarksRepository.findById(incidentId)
//	    		.orElseThrow(() -> new ResourceNotFoundException("Incident", "id", incidentId));

		// policeRemarksDTO.setIncidentId(incident.getIncidentId());
		policeRemarksDto.setId(id);
		policeRemarksDto.setIncidentId(incidentId);
		PoliceRemarksDto updatedPoliceRemarksDTO = this.policeRemarksService.updatePoliceRemarks(id, policeRemarksDto);
		return ResponseEntity.ok(updatedPoliceRemarksDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deletePoliceRemarks(@PathVariable Integer id) {
		this.policeRemarksService.deletePoliceRemarks(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Police remarks deleted successfully", true),
				HttpStatus.OK);
	}

}

/*
 * {
   "incidentId": "DTC202400010",
    "name": "Officer Karen Brown",
  "location": "Snowy Ridge Road",
  "policeStationName": "Mountain Police Department",
  "isIncidentSeen": true,
  "description": "The bus skidded on ice and crashed into a guardrail.",
  "investigationReport": {
    "reportName": "Bus Accident Report",
    "address": "789 Snowy Road",
    "injuredDescription": "Several minor injuries reported.",
    "remarks": "Weather conditions were a significant factor."
  },
  "drName": "Dr. Laura Adams",
  "drAddress": "123 Health Ave",
  "hospitalName": "Mountain View Hospital",
  "referenceNo": "REF112233"
}
*/
