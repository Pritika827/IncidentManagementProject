package com.transline.services;

import java.util.List;

import com.transline.dtos.PoliceRemarksDto;

public interface PoliceRemarksService {

	PoliceRemarksDto savePoliceRemarks(PoliceRemarksDto remarksDTO);

	PoliceRemarksDto getPoliceRemarksById(Integer id);

	List<PoliceRemarksDto> getAllPoliceRemarks();

	void deletePoliceRemarks(Integer id);

	PoliceRemarksDto updatePoliceRemarks(Integer id, PoliceRemarksDto policeRemarksDto);

}