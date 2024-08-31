package com.transline.services;

import java.util.List;

import com.transline.dtos.ReasonDto;

public interface ReasonService {

	ReasonDto saveReason(ReasonDto reasonDto);

	ReasonDto getReasonById(Integer id);

	ReasonDto getReasonByIncidentId(String incidentId);
	
	List<ReasonDto> getAllReasons();

	ReasonDto updateReason(ReasonDto reasonDto, Integer id);

	void deleteReason(Integer id);

}
