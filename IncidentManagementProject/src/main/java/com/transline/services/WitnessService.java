package com.transline.services;

import java.util.List;

import com.transline.dtos.WitnessAndOtherDto;
import com.transline.dtos.WitnessDto;

public interface WitnessService {

	WitnessDto saveWitness(WitnessDto witnessDto);

	WitnessDto getWitnessById(Integer id);

	List<WitnessDto> getAllWitness();

	WitnessDto updateWitness(Integer id, WitnessDto witnessDto);

	void deleteWitness(Integer id);
	
//	public List<WitnessAndOtherDTO> getAllWitnessesSortedByType();
}
