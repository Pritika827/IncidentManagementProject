package com.transline.entities;

import lombok.Data;

@Data
public class JwtRequest {

	private String userName;
//	private String email;
	private String password;
	private String cmpCd;
	
	//email,password

}
