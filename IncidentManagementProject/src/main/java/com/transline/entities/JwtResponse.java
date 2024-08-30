package com.transline.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {

	private String token;
//	private String email;
//	private String username;
	//```private String role;
	
	
	//token,username
}
