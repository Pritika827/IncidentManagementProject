package com.transline.services;

import java.util.List;

import com.transline.dtos.StaffsDto;

public interface StaffService {

	StaffsDto saveStaffs(StaffsDto staffsDto);

	List<StaffsDto> getAllStaffDetails();

	StaffsDto getStaffById(Integer staffId);

	StaffsDto updateStaffs(StaffsDto staffsDto, Integer staffId);

	void deleteStaffs(Integer staffId);

}
