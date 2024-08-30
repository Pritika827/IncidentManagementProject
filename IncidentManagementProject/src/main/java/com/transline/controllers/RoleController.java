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

import com.transline.dtos.RoleDto;
import com.transline.services.RoleService;
import com.transline.utils.ApiResponse;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "false")
public class RoleController {
	@Autowired
	private RoleService roleService;

	@PostMapping
	public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(roleService.saveRole(roleDto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<RoleDto> getRoleById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.roleService.getRoleById(id));
	}

	@GetMapping
	public ResponseEntity<List<RoleDto>> getAllRole() {
		return ResponseEntity.ok(this.roleService.getAllRole());
	}

	@PutMapping("/{id}")
	public ResponseEntity<RoleDto> updateRole(@RequestBody RoleDto roleDto, @PathVariable Integer id) {
		RoleDto updatedRole = this.roleService.updateRole(id, roleDto);
		return ResponseEntity.ok(updatedRole);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteRole(@PathVariable Integer id) {
		this.roleService.deleteRole(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("role deleted successfully", true), HttpStatus.OK);
	}

}
