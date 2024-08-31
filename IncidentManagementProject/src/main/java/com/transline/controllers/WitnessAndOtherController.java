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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.transline.dtos.ReasonDto;
import com.transline.dtos.WitnessAndOtherDto;
import com.transline.services.WitnessAndOtherService;
import com.transline.utils.ApiResponse;

@RestController
@RequestMapping("/api/witnessAndOther")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "false")
public class WitnessAndOtherController {

	private static final Logger logger = LoggerFactory.getLogger(PoliceRemarksController.class);

	@Autowired
	private WitnessAndOtherService witnessService;

	@PostMapping
	public ResponseEntity<WitnessAndOtherDto> createWitness(@RequestBody WitnessAndOtherDto witnessDTO) {
		WitnessAndOtherDto savedWitness = witnessService.saveWitness(witnessDTO);
		return ResponseEntity.ok(savedWitness);

	}

	@GetMapping("/{id}")
	public ResponseEntity<WitnessAndOtherDto> getWitnessAndOtherById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.witnessService.getWitnessById(id));
	}

	@GetMapping
	public ResponseEntity<List<WitnessAndOtherDto>> getWitnessAndOther() {
		List<WitnessAndOtherDto> witnessAndOtherDto = witnessService.getAllWitness();
		return ResponseEntity.ok(witnessAndOtherDto);
	}

	@GetMapping("/incident/{incidentId}")
	public ResponseEntity<WitnessAndOtherDto> getWitnessByIncidentId(@PathVariable String incidentId) {
		WitnessAndOtherDto witnessAndOtherDto = witnessService.getWitnessByIncidentId(incidentId);
		return ResponseEntity.ok(witnessAndOtherDto);
	}

	@PutMapping("/{id}/{incidentId}")
	public ResponseEntity<WitnessAndOtherDto> updateWitnessAndOther(@PathVariable Integer id,
			@RequestBody WitnessAndOtherDto witnessAndOtherDto, @PathVariable String incidentId) {
		witnessAndOtherDto.setId(id);
		witnessAndOtherDto.setIncidentId(incidentId);
		WitnessAndOtherDto updatedwitness = this.witnessService.updateWitnessAndOther(witnessAndOtherDto, id);
		return ResponseEntity.ok(updatedwitness);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteWitnessAndOther(@PathVariable Integer id) {
		this.witnessService.deleteWitness(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Police remarks deleted successfully", true),
				HttpStatus.OK);
	}

	@GetMapping("/sortedByType")
	public ResponseEntity<List<WitnessAndOtherDto>> getAllWitnessesSortedByType() {
		List<WitnessAndOtherDto> sortedWitnesses = witnessService.getAllWitnessesSortedByType();
		return ResponseEntity.ok(sortedWitnesses);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<WitnessAndOtherDto>> getWitnessAndOthersByPrefix(@RequestParam String prefix) {
		List<WitnessAndOtherDto> witnessesAndOthers = witnessService.getWitnessAndOthersByPrefix(prefix);
		return ResponseEntity.ok(witnessesAndOthers);
	}
}

/*
{
"incidentId": "DTC202400005",
"name": "Emily White",
"address": "654 Maple St, Riverside",
"isInBus": false,
"seatDirection": null,
"seenIncident": false,
"busSpeed": 45,
"isHorned": false,
"isBeaked": true,
"tryToStopIncident": false,
"culprit": "Unknown driver",
"description": "The bus was sideswiped by a speeding car, which then fled the scene.",
"date": "2024-08-26",
"time": "19:00:00",
"witnessType": "W"
}
*/