package com.transline.services;

import java.util.List;

import com.transline.dtos.InsuranceDto;
import com.transline.dtos.PoliceRemarksDto;

public interface InsuranceService {

	InsuranceDto saveInsurance(InsuranceDto insuranceDto);

	InsuranceDto getInsuranceById(Integer id);

	List<InsuranceDto> getAllPoliceRemarks();

	void deleteInsuranceDTO(Integer id);

// updateInsuranceDTO(Integer id, InsuranceDTO insuranceDTO);

	InsuranceDto updateInsurance(InsuranceDto insuranceDto, Integer id);

	InsuranceDto getInsuranceByIncidentId(String incidentId);
}
