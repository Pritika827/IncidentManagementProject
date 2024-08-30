package com.transline.servicesImp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transline.dtos.WitnessDto;
import com.transline.entities.Witness;
import com.transline.repositories.IncidentRepository;
import com.transline.repositories.WitnessRepository;
import com.transline.services.WitnessService;

@Service
public class WitnessServiceImpl implements WitnessService {

	@Autowired
	private WitnessRepository witnessRepository;

	@Autowired
	private IncidentRepository incidentRepository;

	// -----------------Model
	// Mapper--------------------------------------------------------------
	@Autowired
	private ModelMapper modelMapper;

	private Witness toEntity(WitnessDto witnessDto) {
		Witness witness = this.modelMapper.map(witnessDto, Witness.class);
		return witness;
	}

	public WitnessDto toDto(Witness witness) {
		WitnessDto witnessDto = new WitnessDto();
		witnessDto.setId(witness.getId());
		witnessDto.setIncidentId(witness.getIncident().getIncidentId());
		witnessDto.setName(witness.getName());
		witnessDto.setAddress(witness.getAddress());
		witnessDto.setIsInBus(witness.getIsInBus());
		witnessDto.setSeatDirection(witness.getSeatDirection());
		witnessDto.setSeenIncident(witness.getSeenIncident());
		witnessDto.setBusSpeed(witness.getBusSpeed());
		witnessDto.setIsHorned(witness.getIsHorned());
		witnessDto.setIsBeaked(witness.getIsBeaked());
		witnessDto.setTryToStopIncident(witness.getTryToStopIncident());
		witnessDto.setCulprit(witness.getCulprit());

		List<WitnessDto.OtherReferenceDTO> otherReferences = witness.getAnyOtherRef().stream().map(ref -> {
			WitnessDto.OtherReferenceDTO dto = new WitnessDto.OtherReferenceDTO();
			dto.setRefName(ref.getRefName());
			dto.setRefAddress(ref.getRefAddress());
			return dto;
		}).collect(Collectors.toList());
		witnessDto.setAnyOtherRef(otherReferences);

		witnessDto.setDescription(witness.getDescription());
		witnessDto.setDate(witness.getDate());
		witnessDto.setTime(witness.getTime());

		return witnessDto;
	}

// ---------------------------------------------------------------------------------------------

	private void validateIncidentId(String incidentId) {
		boolean exists = incidentRepository.existsById(incidentId);
		if (!exists) {
			throw new RuntimeException("Incident with id " + incidentId + " does not exist.");
		}
	}

	@Override
	public WitnessDto saveWitness(WitnessDto witnessDto) {
		validateIncidentId(witnessDto.getIncidentId());
		Witness witness = this.toEntity(witnessDto);
		Witness savedWitness = witnessRepository.save(witness);
		return this.toDto(savedWitness);
	}

	@Override
	public WitnessDto getWitnessById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WitnessDto> getAllWitness() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WitnessDto updateWitness(Integer id, WitnessDto witnessDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteWitness(Integer id) {
		// TODO Auto-generated method stub

	}

}
