package com.transline.controllers;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transline.dtos.OfficeDto;
import com.transline.entities.Office;
import com.transline.services.OfficeService;
import com.transline.utils.ApiResponse;

@RestController
@RequestMapping("/api/office")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "false")
public class OfficeController {

	@Autowired
	private OfficeService officeService;

	@PostMapping
	public ResponseEntity<OfficeDto> createNewOffice(@RequestBody OfficeDto officeDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(officeService.saveOffice(officeDto));
	}

	@GetMapping("/{offCd}")
	public ResponseEntity<OfficeDto> getOfficeById(@PathVariable String offCd) {
		return ResponseEntity.ok(this.officeService.getOfficeById(offCd));
	}

	@GetMapping
	public ResponseEntity<List<OfficeDto>> getAllOfficeDetails() {
		return ResponseEntity.ok(this.officeService.getAllOfficeDetails());
	}

	@PutMapping("/{offCd}")
	public ResponseEntity<Office> updateOffice(@RequestBody OfficeDto officeDto, @PathVariable String offCd) {
		Office updatedOffice = this.officeService.updateOfficeDto(officeDto, offCd);
		return ResponseEntity.ok(updatedOffice);

	}

	@DeleteMapping("{offCd}")
	public ResponseEntity<ApiResponse> deleteOffice(@PathVariable String offCd) {
		this.officeService.deleteOffice(offCd);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Office deleted successfully", true), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/secure-endpoint")
    public ResponseEntity<String> getSecureData() {
		 System.out.println("Authorities: ");
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authorities: " + auth.getAuthorities());
        return ResponseEntity.ok("This is a secure data accessible only by ADMIN.");
    }
}
