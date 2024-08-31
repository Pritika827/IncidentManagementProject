package com.transline.services;

import java.util.List;

import com.transline.dtos.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, String userId);

	UserDto getUserById(String userId);

	List<UserDto> getAllUsers();

	void deleteUser(String userId);
}
