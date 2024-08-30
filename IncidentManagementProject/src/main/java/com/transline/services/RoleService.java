package com.transline.services;

import java.util.List;

import com.transline.dtos.RoleDto;

public interface RoleService {

	RoleDto saveRole(RoleDto roleDto);

	RoleDto getRoleById(Integer id);

	List<RoleDto> getAllRole();

	void deleteRole(Integer id);

	RoleDto updateRole(Integer id, RoleDto roleDto);

}
