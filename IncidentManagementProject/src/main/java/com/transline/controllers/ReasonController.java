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

import com.transline.dtos.ReasonDto;
import com.transline.services.ReasonService;
import com.transline.utils.ApiResponse;

@RestController
@RequestMapping("/api/reason")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "false")
public class ReasonController {

	private static final Logger logger = LoggerFactory.getLogger(PoliceRemarksController.class);

	@Autowired
	private ReasonService reasonService;

	@PostMapping
	public ResponseEntity<ReasonDto> createReason(@RequestBody ReasonDto reasonDto) {
		ReasonDto savedReason = reasonService.saveReason(reasonDto);
		return ResponseEntity.ok(savedReason);

	}

	@GetMapping("/{id}")
	public ResponseEntity<ReasonDto> getPoliceReasonById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.reasonService.getReasonById(id));
	}

	@GetMapping
	public ResponseEntity<List<ReasonDto>> getAllPoliceReason() {
		List<ReasonDto> reasonDtos = reasonService.getAllReasons();
		return ResponseEntity.ok(reasonDtos);
	}

	@PutMapping("/{id}/{incidentId}")
	public ResponseEntity<ReasonDto> updatePoliceReason(@PathVariable Integer id, @RequestBody ReasonDto reasonDto,
			@PathVariable String incidentId) {
		reasonDto.setId(id);
		reasonDto.setIncidentId(incidentId);
		ReasonDto updatedReasonDTO = this.reasonService.updateReason(reasonDto, id);
		return ResponseEntity.ok(updatedReasonDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteReason(@PathVariable Integer id) {
		this.reasonService.deleteReason(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Reason deleted successfully", true), HttpStatus.OK);
	}

}
