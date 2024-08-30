package com.transline.servicesImp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transline.dtos.RoleDto;
import com.transline.entities.Role;
import com.transline.exceptions.ResourceNotFoundException;
import com.transline.repositories.RoleRepository;
import com.transline.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	// -----------------Model
	// Mapper--------------------------------------------------------------
	@Autowired
	private ModelMapper modelMapper;

	private Role dtoToRole(RoleDto roleDto) {
		Role role = this.modelMapper.map(roleDto, Role.class);
		return role;
	}

	public RoleDto roleToDto(Role role) {
		RoleDto roleDto = this.modelMapper.map(role, RoleDto.class);
		return roleDto;
	}

	// ---------------------------------------------------------------------------------------------

	@Override
	public RoleDto saveRole(RoleDto roleDto) {
		Role role = this.dtoToRole(roleDto);
		Role saveRole = this.roleRepository.save(role);
		return this.roleToDto(saveRole);
	}

	@Override
	public RoleDto getRoleById(Integer id) {
		Role role = this.roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("role", "id", id));
		return this.roleToDto(role);
	}

	@Override
	public List<RoleDto> getAllRole() {
		List<Role> roles = this.roleRepository.findAll();
		List<RoleDto> roleDtos = roles.stream().map(r -> this.roleToDto(r)).collect(Collectors.toList());
		return roleDtos;
	}

	@Override
	public void deleteRole(Integer id) {
		Role role = this.roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("role", "id", id));
		this.roleRepository.delete(role);
	}

	@Override
	public RoleDto updateRole(Integer id, RoleDto roleDto) {
		Role role = this.roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("role", "id", id));
		role.setName(roleDto.getName());

		Role updatedRole = this.roleRepository.save(role);
		RoleDto roleDto2 = this.roleToDto(updatedRole);
		return roleDto2;
	}

}
