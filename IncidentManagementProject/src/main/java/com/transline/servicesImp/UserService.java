package com.transline.servicesImp;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.transline.dtos.OfficeDto;
import com.transline.dtos.UserDto;
import com.transline.entities.CompanyMst;
import com.transline.entities.Office;
import com.transline.entities.User;
import com.transline.exceptions.ResourceNotFoundException;
import com.transline.repositories.CompanyMstRepository;
import com.transline.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	private CompanyMstRepository companyMstRepository;

// -----------------Model Mapper--------------------------------------------------------------
	@Autowired
	private ModelMapper modelMapper;

//	public User dtoToOffice(OfficeDto officeDto) {
//		Office offices = new Office();
//		offices.setOffCd(officeDto.getOffCd());
//
//		// Fetch CompanyMst entity using cmpCd
//		CompanyMst companyMst = companyMstRepository.findByCmpCd(officeDto.getCmpCd());
//
//		if (companyMst != null) {
//			offices.setCompanyMst(companyMst);
//		} else {
//			throw new ResourceNotFoundException("CompanyMst", "cmpCd", officeDto.getCmpCd());
//		}
//		offices.setOffName(officeDto.getOffName());
//		offices.setDisable(officeDto.getDisable());
//		offices.setWorkingStatus(officeDto.getWorkingStatus());
//
//		return offices;
//	}

//	public User dtoToUser(UserDto userDto) {
//		User user=new User();
//		user.setUserName(null);
//		
//		return user;
//	}
	
//	public OfficeDto officeToDto(Office office) {
//		OfficeDto officeDto = new OfficeDto();
//		officeDto.setOffCd(office.getOffCd());
//
//		if (office.getCompanyMst() != null) {
//			officeDto.setCmpCd(office.getCompanyMst().getCmpCd());
//		}
//
//		officeDto.setOffName(office.getOffName());
//		officeDto.setWorkingStatus(office.getWorkingStatus());
//
//		return officeDto;
//	}

// ---------------------------------------------------------------------------------------------



	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public User createUser(User user) {
		user.setUserId(UUID.randomUUID().toString());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
}
