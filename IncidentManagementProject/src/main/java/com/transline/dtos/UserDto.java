package com.transline.dtos;


import java.util.HashSet;
import java.util.Set;
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
	private String userName; // User's email
	private String password;
	private String status;
	private String offCd;
	private Integer id;
	private String cmpCd;
	private Set<RoleDto> roles;
	//private String role;
	// private Set<RoleDto> roles = new HashSet<>();
	 
	 

}
