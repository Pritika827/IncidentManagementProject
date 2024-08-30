package com.transline.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transline.dtos.ReasonDto;
import com.transline.dtos.WitnessDto;
import com.transline.services.WitnessService;

@RestController
@RequestMapping("/api/witness")
@CrossOrigin(origins = "*",allowedHeaders = "*",allowCredentials = "false")
public class WitnessController {

	private static final Logger logger = LoggerFactory.getLogger(PoliceRemarksController.class);
	
	@Autowired
	private WitnessService witnessService;
	
	@PostMapping
	public ResponseEntity<WitnessDto> createWitness(@RequestBody WitnessDto witnessDto) {
		WitnessDto savedWitness=witnessService.saveWitness(witnessDto);
		return ResponseEntity.ok(savedWitness);
	
	}
}
