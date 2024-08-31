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

import com.transline.dtos.InsuranceDto;
import com.transline.dtos.PoliceRemarksDto;
import com.transline.services.InsuranceService;
import com.transline.servicesImp.PoliceRemarksServiceImpl;
import com.transline.utils.ApiResponse;

@RestController
@RequestMapping("/api/insurance")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "false")
public class InsuranceController {

	private static final Logger logger = LoggerFactory.getLogger(PoliceRemarksServiceImpl.class);

	@Autowired
	private InsuranceService insuranceService;

	@PostMapping
	public ResponseEntity<InsuranceDto> createInsurance(@RequestBody InsuranceDto insuranceDto) {
		InsuranceDto savedinsuranceDTO = insuranceService.saveInsurance(insuranceDto);
		return ResponseEntity.ok(savedinsuranceDTO);
	}

	@GetMapping("{id}")
	public ResponseEntity<InsuranceDto> getInsuranceById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.insuranceService.getInsuranceById(id));
	}

	@GetMapping
	public ResponseEntity<List<InsuranceDto>> getAllInsurance() {
		List<InsuranceDto> insurances = insuranceService.getAllPoliceRemarks();
		return ResponseEntity.ok(insurances);
	}
	
	@GetMapping("/incident/{incidentId}")
	public ResponseEntity<InsuranceDto> getInsuranceByIncidentId(@PathVariable String incidentId) {
		InsuranceDto insuranceDto = insuranceService.getInsuranceByIncidentId(incidentId);
		return ResponseEntity.ok(insuranceDto);
	}

	@PutMapping("{id}/{incidentId}")
	public ResponseEntity<InsuranceDto> updateInsurance(@PathVariable Integer id,
			@RequestBody InsuranceDto insuranceDto,@PathVariable String incidentId) {
		insuranceDto.setId(id);
		insuranceDto.setIncidentId(incidentId);
		InsuranceDto updatedInsurance = this.insuranceService.updateInsurance(insuranceDto, id);
		return ResponseEntity.ok(updatedInsurance);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<ApiResponse> deleteInsurance(@PathVariable Integer id) {
		this.insuranceService.deleteInsuranceDTO(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Insurance deleted successfully", true), HttpStatus.OK);
	}

}
