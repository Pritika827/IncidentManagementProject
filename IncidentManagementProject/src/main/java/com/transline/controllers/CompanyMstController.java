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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transline.dtos.CompanyMstDto;
import com.transline.dtos.UploadsDto;
import com.transline.services.CompanyMstService;
import com.transline.utils.ApiResponse;

import io.jsonwebtoken.io.IOException;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/companyMst")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "false")
public class CompanyMstController {

	@Autowired
	private CompanyMstService companyMstService;

	@PostMapping
	public ResponseEntity<CompanyMstDto> createUploadFile(@RequestPart("jsonData") String logo,
			@RequestPart("logo") MultipartFile file) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.println("------------UPLOAD JSON RECEIVED ----------------");
			CompanyMstDto companyMstDto = objectMapper.readValue(logo, CompanyMstDto.class);
			System.out.println("------------UPLOAD PARSED ----------------");
			CompanyMstDto savedUploadFile = companyMstService.saveCompanyMast(companyMstDto, file);
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

	@GetMapping("/{cmpCd}")
	public ResponseEntity<CompanyMstDto> getCompanyMstById(@PathVariable String cmpCd) {
		return ResponseEntity.ok(this.companyMstService.getAllCompanyMstById(cmpCd));
	}

	@GetMapping
	public ResponseEntity<List<CompanyMstDto>> getAllCompanyMst() {
		return ResponseEntity.ok(this.companyMstService.getAllCompanyMst());

	}

	@PutMapping("/{cmpCd}")
	public ResponseEntity<CompanyMstDto> updateCompanyMst(@RequestPart("jsonData") String jsonData,
			@RequestPart("logo") MultipartFile file,@PathVariable String cmpCd) throws java.io.IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			CompanyMstDto companyMstDto = objectMapper.readValue(jsonData, CompanyMstDto.class);
		//	companyMstDto.setId(id);
			companyMstDto.setCmpCd(cmpCd);

			CompanyMstDto updatedCompanyMst = this.companyMstService.updateCompanyMst(cmpCd, companyMstDto, file);
			return ResponseEntity.ok(updatedCompanyMst);

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("{cmpCd}")
	public ResponseEntity<ApiResponse> deleteCompanyMst(@PathVariable String cmpCd){
		companyMstService.deleteCompanyById(cmpCd);
		return new ResponseEntity<ApiResponse>(new ApiResponse("upload deleted successfully", true), HttpStatus.OK);
	}	

}
