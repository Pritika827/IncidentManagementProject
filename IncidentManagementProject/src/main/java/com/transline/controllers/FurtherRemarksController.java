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

import com.transline.dtos.FurtherRemarksDto;
import com.transline.dtos.InspectionReportDto;
import com.transline.services.FurtherRemarksService;
import com.transline.utils.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/furtherRemarks")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "false")
@Tag(name = "Further Remarks Management", description = "Operations related to further remarks management")
public class FurtherRemarksController {

	@Autowired
	private FurtherRemarksService remarksService;

	@PostMapping
	@Operation(summary = "Create further remarks", description = "Add a new further remarks to the system")
	public ResponseEntity<FurtherRemarksDto> createNewFurtherRemarks(@RequestBody FurtherRemarksDto remarksDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(remarksService.saveFurtherRemarks(remarksDto));
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get further remark by ID", description = "Retrieve a further remark by their ID")
	public ResponseEntity<FurtherRemarksDto> getFurtherRemarksById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.remarksService.getFurtherRemarksById(id));
	}

	@GetMapping
	@Operation(summary = "Get all further remarks", description = "Retrieve a list of all further remarks")
	public ResponseEntity<List<FurtherRemarksDto>> getAllFurtherRemarksDetails() {
		return ResponseEntity.ok(this.remarksService.getAllFurtherRemarks());
	}

	@PutMapping("/{id}/{incidentId}")
	@Operation(summary = "Update further remark", description = "Update an existing further remark's details by their ID")
	public ResponseEntity<FurtherRemarksDto> updateFurtherRemarks(@RequestBody FurtherRemarksDto remarksDto,
			@PathVariable Integer id, @PathVariable String incidentId) {
		remarksDto.setId(id);
		remarksDto.setIncidentId(incidentId);
		FurtherRemarksDto updatedRemarksDto = this.remarksService.updateFurtherRemarksDto(remarksDto, id);
		return ResponseEntity.ok(updatedRemarksDto);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete further remark", description = "Delete a further remark by their ID")
	public ResponseEntity<ApiResponse> deleteupdatedRemarks(@PathVariable Integer id) {
		this.remarksService.deleteFurtherRemarks(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("incident deleted successfully", true), HttpStatus.OK);
	}

//	@GetMapping("/incident/{incidentId}")
//	public ResponseEntity<List<FurtherRemarksDto>> getFurtherRemarksByIncidentId(@PathVariable String incidentId) {
//		List<FurtherRemarksDto> furtherRemarks = remarksService.getAllFutherRemarksByIncidentId(incidentId);
//		return ResponseEntity.ok(furtherRemarks);
//	}
	
	@GetMapping("/incident/{incidentId}")
	public ResponseEntity<FurtherRemarksDto> getFutherRemarkByIncidentId(@PathVariable String incidentId) {
		FurtherRemarksDto furtherRemarksDto = remarksService.getFutherRemarkByIncidentId(incidentId);
		return ResponseEntity.ok(furtherRemarksDto);
	}
}

/*
 {
    "incidentId": "DTC202400009",
 "reportedBy": "Ethan White",
  "description": "A minor rear-end collision occurred at a stop sign. Both vehicles suffered slight damages.",
  "date": "2024-08-31",
  "time": "08:00:00"
  }*/
