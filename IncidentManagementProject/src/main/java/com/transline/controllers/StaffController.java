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

import com.transline.dtos.StaffsDto;
import com.transline.servicesImp.StaffServiceImpl;
import com.transline.utils.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/staffs")
@CrossOrigin(origins = "*",allowedHeaders = "*",allowCredentials = "false")
@Tag(name = "Staff Management", description = "Operations related to staff management")
public class StaffController {

	@Autowired
	private StaffServiceImpl staffService;

	@PostMapping
	@Operation(summary = "Create staff", description = "Add a new staff to the system")
	public ResponseEntity<StaffsDto> createNewStaff(@RequestBody StaffsDto staffsDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(staffService.saveStaffs(staffsDto));
	}

	@GetMapping("/{staffId}")
	@Operation(summary = "Get staff by ID", description = "Retrieve a staff by their ID")
	public ResponseEntity<StaffsDto> getStaffById(@PathVariable Integer staffId) {
		return ResponseEntity.ok(this.staffService.getStaffById(staffId));
	}

	@GetMapping
	@Operation(summary = "Get all staffs", description = "Retrieve a list of all staffs")
	public ResponseEntity<List<StaffsDto>> getAllStaffsDetails() {
		return ResponseEntity.ok(this.staffService.getAllStaffDetails());
	}

	@PutMapping("/{staffId}")
	@Operation(summary = "Update staff", description = "Update an existing staff's details by their ID")
	public ResponseEntity<StaffsDto> updateStaffs(@RequestBody StaffsDto staffsDto, @PathVariable Integer staffId) {
		StaffsDto updatedStaffs = this.staffService.updateStaffs(staffsDto, staffId);
		return ResponseEntity.ok(updatedStaffs);
	}

	@DeleteMapping("/{staffId}")
	@Operation(summary = "Delete staff", description = "Delete a staff by their ID")
	public ResponseEntity<ApiResponse> deleteStaff(@PathVariable Integer staffId) {
		this.staffService.deleteStaffs(staffId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("staff deleted successfully", true), HttpStatus.OK);
	}

}
