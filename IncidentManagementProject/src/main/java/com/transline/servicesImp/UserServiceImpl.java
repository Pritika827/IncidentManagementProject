package com.transline.servicesImp;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.transline.dtos.RoleDto;
import com.transline.dtos.UserDto;
import com.transline.entities.CompanyMst;
import com.transline.entities.Office;
import com.transline.entities.Role;
import com.transline.entities.User;
import com.transline.exceptions.ResourceNotFoundException;
import com.transline.repositories.CompanyMstRepository;
import com.transline.repositories.OfficeRepository;
import com.transline.repositories.RoleRepository;
import com.transline.repositories.UserRepository;
import com.transline.services.UserService;

//@Service
//public class UserServiceImpl {
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
//	@Autowired
//	private CompanyMstRepository companyMstRepository;
//
//	public List<User> getUsers() {
//		return userRepository.findAll();
//	}
//
//	public User createUser(User user) {
//		user.setUserId(UUID.randomUUID().toString());
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		return userRepository.save(user);
//	}
//}

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private CompanyMstRepository companyMstRepository;
	
	@Autowired
	private OfficeRepository officeRepository;

//-------------------------------------------MODEL MAPPER---------------------------------------------------------------------	
	@Autowired
	private ModelMapper modelMapper;

	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);

		user.setUserId(userDto.getUserId());
		user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		user.setStatus(userDto.getStatus());
		// user.setRoles(userDto.getRoles());
	//	user.setCmpCd(userDto.getCmpCd());
		
		// Fetch CompanyMst entity using cmpCd
				CompanyMst companyMst = companyMstRepository.findByCmpCd(userDto.getCmpCd());

				if (companyMst != null) {
					user.setCompanyMst(companyMst);
				} else {
					throw new ResourceNotFoundException("CompanyMst", "cmpCd", userDto.getCmpCd());
				}
				
		//user.setOffCd(userDto.getOffCd());

				Office office=officeRepository.findByOffCd(userDto.getOffCd());
				if(office!=null) {
					user.setOffice(office);
				}else {
					throw new ResourceNotFoundException("Office","offCd",userDto.getOffCd());
				}
	
		if (userDto.getRoles() != null) {
			Set<Role> roles = userDto.getRoles().stream().map(roleDto -> {
				return roleRepo.findById(roleDto.getId())
						.orElseThrow(() -> new RuntimeException("Role not found: " + roleDto.getId()));
			}).collect(Collectors.toSet());
			user.setRoles(roles);
		}

		return user;
	}

	public UserDto userToDto(User user) {
	//	UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
		// Create a new UserDto instance
	    UserDto userDto = this.modelMapper.map(user, UserDto.class);

	    // Map basic fields
	    userDto.setUserId(user.getUserId());
	    userDto.setUserName(user.getUsername());
	    userDto.setPassword(user.getPassword()); // Password should be handled carefully
	    userDto.setStatus(user.getStatus());
	    userDto.setCmpCd(user.getCompanyMst().getCmpCd());
	    userDto.setOffCd(user.getOffice().getOffCd());

	    // Map roles if present
	    if (user.getRoles() != null) {
	        Set<RoleDto> roleDtos = user.getRoles().stream().map(role -> {
	            RoleDto roleDto = new RoleDto();
	            roleDto.setId(role.getId());
	            roleDto.setName(role.getName());
	            return roleDto;
	        }).collect(Collectors.toSet());
	        userDto.setRoles(roleDtos);
	    }
		return userDto;
	}

//---------------------------------------------------------------------------------------------------------------------	

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);

		String prefix = "user";
		Integer maxNumber = userRepo.findMaxNumberForPrefix(prefix).orElse(0);
		Integer nextNumber = maxNumber + 1;
		String formattedNumber = String.format("%03d", nextNumber);
		String newUserId = prefix + formattedNumber;

		user.setUserId(newUserId);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, String userId) {

		User existingUser = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		// Map DTO to entity, while updating specific fields
		User user = this.dtoToUser(userDto);

		// Update fields of the existing user
		existingUser.setUserName(user.getUsername());
		existingUser.setPassword(user.getPassword()); // Consider encoding password if it's updated
		existingUser.setStatus(user.getStatus());
	//	existingUser.setCmpCd(user.getCmpCd());
		
		
		// Fetch CompanyMst entity using cmpCd
		CompanyMst companyMst = companyMstRepository.findByCmpCd(userDto.getCmpCd());
		if (companyMst != null) {
			user.setCompanyMst(companyMst);
		} else {
			throw new ResourceNotFoundException("CompanyMst", "cmpCd", userDto.getCmpCd());
		}
		
		//existingUser.setOffCd(user.getOffCd());
		
		Office office = officeRepository.findByOffCd(userDto.getOffCd());
		if (office != null) {
			user.setOffice(office);
		} else {
			throw new ResourceNotFoundException("Office", "offCd", userDto.getOffCd());
		}
		

		if (userDto.getRoles() != null) {
			Set<Role> roles = userDto.getRoles().stream().map(roleDto -> {
				return roleRepo.findById(roleDto.getId())
						.orElseThrow(() -> new RuntimeException("Role not found: " + roleDto.getId()));
			}).collect(Collectors.toSet());
			existingUser.setRoles(roles);
		}
		User updatedUser = this.userRepo.save(existingUser);
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(String userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}
	

	@Override
	public void deleteUser(String userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);
	}

}
