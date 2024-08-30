package com.transline.dtos;

import lombok.Data;

@Data
public class AuthRequestDto {
	private String userName;
    private String userPassword;
}
