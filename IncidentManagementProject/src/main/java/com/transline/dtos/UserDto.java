package com.transline.dtos;


import java.util.HashSet;
import java.util.Set;

import com.transline.enums.Role;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

	private String userId;
	
//	@NotEmpty(message = "user name is required !!")
	private String userName; // User's email
	
//	@NotEmpty
	private String password;
	private String status;
	private String offCd;
//	private Integer id;
	private String cmpCd;
//	private Role role;
	private Set<RoleDto> roles;
	//private String role;
	// private Set<RoleDto> roles = new HashSet<>();
	 
	 

}
