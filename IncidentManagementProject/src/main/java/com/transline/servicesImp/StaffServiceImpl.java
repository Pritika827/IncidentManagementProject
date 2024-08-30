package com.transline.servicesImp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transline.dtos.StaffsDto;
import com.transline.entities.Staffs;
import com.transline.exceptions.ResourceNotFoundException;
import com.transline.repositories.StaffRepository;
import com.transline.services.StaffService;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffRepository staffrepository;

//-----------------Model Mapper--------------------------------------------------------------
	@Autowired
	private ModelMapper modelMapper;

	private Staffs dtoToStaff(StaffsDto staffsDto) {
		Staffs staffs = this.modelMapper.map(staffsDto, Staffs.class);
		return staffs;
	}

	public StaffsDto staffToDto(Staffs staffs) {
		StaffsDto staffsDto = this.modelMapper.map(staffs, StaffsDto.class);
		return staffsDto;
	}
//---------------------------------------------------------------------------------------------	

	@Override
	public StaffsDto saveStaffs(StaffsDto staffsDto) {
		Staffs staffs = this.dtoToStaff(staffsDto);
		Staffs savedStaffs = this.staffrepository.save(staffs);
		return this.staffToDto(savedStaffs);
	}

	@Override
	public List<StaffsDto> getAllStaffDetails() {
		List<Staffs> staffs = this.staffrepository.findAll();
		List<StaffsDto> staffsDtos = staffs.stream().map(staff -> this.staffToDto(staff)).collect(Collectors.toList());
		return staffsDtos;
	}

	@Override
	public StaffsDto getStaffById(Integer staffId) {
		Staffs staffs = this.staffrepository.findById(staffId)
				.orElseThrow(() -> new ResourceNotFoundException("staff", "id", staffId));
		return this.staffToDto(staffs);
	}

	@Override
	public StaffsDto updateStaffs(StaffsDto staffsDto, Integer staffId) {
		Staffs staffs = this.staffrepository.findById(staffId)
				.orElseThrow(() -> new ResourceNotFoundException("staffs", "id", staffId));
		staffs.setName(staffsDto.getName());
		staffs.setLicenseNumber(staffsDto.getLicenseNumber());
		staffs.setVehicleNo(staffsDto.getVehicleNo());
		staffs.setAge(staffsDto.getAge());
		staffs.setBloodGroup(staffsDto.getBloodGroup());

		Staffs updatedStaff = this.staffrepository.save(staffs);
		StaffsDto staffsDto2 = this.staffToDto(updatedStaff);
		return staffsDto2;
	}

	@Override
	public void deleteStaffs(Integer staffId) {
		Staffs staffs = this.staffrepository.findById(staffId)
				.orElseThrow(() -> new ResourceNotFoundException("staff", "Id", staffId));
		this.staffrepository.delete(staffs);
	}

}
