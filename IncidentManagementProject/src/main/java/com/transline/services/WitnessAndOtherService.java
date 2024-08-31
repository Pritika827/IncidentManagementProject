package com.transline.services;

import java.util.List;

import com.transline.dtos.ReasonDto;
import com.transline.dtos.WitnessAndOtherDto;

public interface WitnessAndOtherService {

	WitnessAndOtherDto saveWitness(WitnessAndOtherDto witnessDTO);

	WitnessAndOtherDto getWitnessById(Integer id);

	List<WitnessAndOtherDto> getAllWitness();

	WitnessAndOtherDto updateWitnessAndOther(WitnessAndOtherDto witnessDTO, Integer id);

	void deleteWitness(Integer id);

	List<WitnessAndOtherDto> getAllWitnessesSortedByType();
	
	public List<WitnessAndOtherDto> getWitnessAndOthersByPrefix(String prefix);
	
	WitnessAndOtherDto getWitnessByIncidentId(String incidentId);
}
