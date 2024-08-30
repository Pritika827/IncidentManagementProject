package com.transline.controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transline.dtos.UploadsDto;
import com.transline.services.UploadsService;
import com.transline.utils.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/uploads")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "false")
public class UploadsController {

	@Autowired
	private UploadsService uploadsService;

	@PostMapping
	public ResponseEntity<UploadsDto> createUploadFile(@RequestPart("upload") String uploadJson,
			@RequestPart("file") MultipartFile file) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.println("------------UPLOAD JSON RECEIVED ----------------");
			UploadsDto uploadsDto = objectMapper.readValue(uploadJson, UploadsDto.class);
			System.out.println("------------UPLOAD PARSED ----------------");
			UploadsDto savedUploadFile = uploadsService.saveUploadFile(uploadsDto, file);
			System.out.println("------------UPLOAD SAVED ----------------");
			return new ResponseEntity<>(savedUploadFile, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{uploadId}")
	public ResponseEntity<UploadsDto> getUploadById(@PathVariable int uploadId) {
		return ResponseEntity.ok(this.uploadsService.getUploadFileById(uploadId));
	}

	@GetMapping
	public ResponseEntity<List<UploadsDto>> getAllUploads() {
		return ResponseEntity.ok(this.uploadsService.getAllUploadFile());
	}

	@PutMapping("/{uploadId}/{refId}")
	@Operation(summary = "Update upload file", description = "Update an existing upload's details by its ID")
	public ResponseEntity<UploadsDto> updateUploadFile(@RequestPart("upload") String uploadJson,
			@RequestPart("file") MultipartFile file, @PathVariable int uploadId, @PathVariable String refId) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// Convert JSON string to UploadsDto object
			UploadsDto uploadsDto = objectMapper.readValue(uploadJson, UploadsDto.class);

			// Set the uploadId and refId in the UploadsDto object
			uploadsDto.setUploadId(uploadId);
			uploadsDto.setRefId(refId);

			// Call the service method to update the upload
			UploadsDto updatedUpload = this.uploadsService.updateUploadFile(uploadId, uploadsDto, file);

			// Return the updated UploadsDto object
			return ResponseEntity.ok(updatedUpload);

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}


	@DeleteMapping("/{uploadId}")
	public ResponseEntity<ApiResponse> deleteUpload(@PathVariable int uploadId) {
		uploadsService.deleteUploadById(uploadId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("upload deleted successfully", true), HttpStatus.OK);
	}

}
