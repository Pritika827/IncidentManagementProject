package com.transline.servicesImp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transline.dtos.WitnessAndOtherDto;
import com.transline.entities.Incidents;
import com.transline.entities.WitnessAndOther;
import com.transline.exceptions.ResourceNotFoundException;
import com.transline.repositories.IncidentRepository;
import com.transline.repositories.WitnessAndOtherRepository;
import com.transline.services.WitnessAndOtherService;

@Service
public class WitnessAndOtherServiceImpl implements WitnessAndOtherService {

	@Autowired
	private WitnessAndOtherRepository witnessAndOtherRepository;

	@Autowired
	private IncidentRepository incidentRepository;

	// -----------------ModelMapper--------------------------------------------------------------
	@Autowired
	private ModelMapper modelMapper;

	private WitnessAndOther toEntity(WitnessAndOtherDto witnessDTO) {
		WitnessAndOther witness = this.modelMapper.map(witnessDTO, WitnessAndOther.class);
		return witness;
	}

	public WitnessAndOtherDto toDto(WitnessAndOther witness) {

		WitnessAndOtherDto witnessDTO = new WitnessAndOtherDto();
		witnessDTO.setId(witness.getId());
		witnessDTO.setIncidentId(witness.getIncident().getIncidentId());
		witnessDTO.setName(witness.getName());
		witnessDTO.setAddress(witness.getAddress());
		witnessDTO.setIsInBus(witness.getIsInBus());
		witnessDTO.setSeatDirection(witness.getSeatDirection());
		witnessDTO.setSeenIncident(witness.getSeenIncident());
		witnessDTO.setBusSpeed(witness.getBusSpeed());
		witnessDTO.setIsHorned(witness.getIsHorned());
		witnessDTO.setIsBeaked(witness.getIsBeaked());
		witnessDTO.setTryToStopIncident(witness.getTryToStopIncident());
		witnessDTO.setCulprit(witness.getCulprit());
		witnessDTO.setDescription(witness.getDescription());
		witnessDTO.setDate(witness.getDate());
		witnessDTO.setTime(witness.getTime());
		witnessDTO.setWitnessType(witness.getWitnessType());

		return witnessDTO;
	}

	// ---------------------------------------------------------------------------------------------

//    @Override
//    public List<WitnessAndOtherDTO> getAllWitnessesSortedByType() {
//        // Fetch all witnesses from the repository
//        List<WitnessAndOther> witnesses = witnessAndOtherRepository.findAll();
//
//        // Sort the list based on the witnessType ('O' before 'W')
//        List<WitnessAndOther> sortedWitnesses = witnesses.stream()
//            .sorted((w1, w2) -> w1.getWitnessType().compareTo(w2.getWitnessType()))
//            .collect(Collectors.toList());
//
//        // Convert to DTO and return
//        return sortedWitnesses.stream()
//            .map(WitnessAndOtherMapper::toDTO)
//            .collect(Collectors.toList());
//    }

	private void validateIncidentId(String incidentId) {
		boolean exists = incidentRepository.existsById(incidentId);
		if (!exists) {
			throw new RuntimeException("Incident with id " + incidentId + " does not exist.");
		}
	}

	@Override
	public WitnessAndOtherDto saveWitness(WitnessAndOtherDto witnessDTO) {
		validateIncidentId(witnessDTO.getIncidentId());
		WitnessAndOther witness = this.toEntity(witnessDTO);
		WitnessAndOther savedWitness = witnessAndOtherRepository.save(witness);
		return this.toDto(savedWitness);
	}

	@Override
	public WitnessAndOtherDto getWitnessById(Integer id) {
		WitnessAndOther witness = witnessAndOtherRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("WitnessAndOther", "id", id));
		return this.toDto(witness);
	}

	@Override
	public List<WitnessAndOtherDto> getAllWitness() {
		List<WitnessAndOther> dtos = this.witnessAndOtherRepository.findAll();
		List<WitnessAndOtherDto> dtos2 = dtos.stream().map(dto -> this.toDto(dto)).collect(Collectors.toList());
		return dtos2;
	}

//	@Override
//	public WitnessAndOtherDTO updateWitness( WitnessAndOtherDTO witnessDTO,Integer id) {
//		WitnessAndOther existingWitness = witnessAndOtherRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("WitnessAndOther", "id", id));
//
//		Incidents associatedIncident = existingWitness.getIncident();
//		if (!associatedIncident.getIncidentId().equals(witnessDTO.getIncidentId())) {
//			throw new IllegalArgumentException("Provided incidentId does not match the existing record.");
//		}
//		existingWitness.setIncident(associatedIncident);
//		existingWitness.setName(witnessDTO.getName());
//		existingWitness.setAddress(witnessDTO.getAddress());
//		existingWitness.setIsInBus(witnessDTO.getIsInBus());
//		existingWitness.setSeatDirection(witnessDTO.getSeatDirection());
//		existingWitness.setSeenIncident(witnessDTO.getSeenIncident());
//		existingWitness.setBusSpeed(witnessDTO.getBusSpeed());
//		existingWitness.setIsHorned(witnessDTO.getIsHorned());
//		existingWitness.setIsBeaked(witnessDTO.getIsBeaked());
//		existingWitness.setTryToStopIncident(witnessDTO.getTryToStopIncident());
//		existingWitness.setCulprit(witnessDTO.getCulprit());
//		existingWitness.setDescription(witnessDTO.getDescription());
//		existingWitness.setDate(witnessDTO.getDate());
//		existingWitness.setTime(witnessDTO.getTime());
//		existingWitness.setWitnessType(witnessDTO.getWitnessType());
//
//		WitnessAndOther updatedWitness = witnessAndOtherRepository.save(existingWitness);
//		return this.toDto(updatedWitness);
//	}

	@Override
	public WitnessAndOtherDto updateWitnessAndOther(WitnessAndOtherDto witnessAndOtherDto, Integer id) {
		// Find the existing WitnessAndOther entity by ID
		WitnessAndOther existingWitnessAndOther = witnessAndOtherRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("WitnessAndOther", "id", id));

		// Find the associated Incident entity from the existing WitnessAndOther record
		Incidents associatedIncident = existingWitnessAndOther.getIncident();
		if (!associatedIncident.getIncidentId().equals(witnessAndOtherDto.getIncidentId())) {
			throw new IllegalArgumentException("Provided incidentId does not match the existing record.");
		}

		// Update the WitnessAndOther entity with new data, but keep the existing
		// Incident
		existingWitnessAndOther.setName(witnessAndOtherDto.getName());
		existingWitnessAndOther.setAddress(witnessAndOtherDto.getAddress());
		existingWitnessAndOther.setIsInBus(witnessAndOtherDto.getIsInBus());
		existingWitnessAndOther.setSeatDirection(witnessAndOtherDto.getSeatDirection());
		existingWitnessAndOther.setSeenIncident(witnessAndOtherDto.getSeenIncident());
		existingWitnessAndOther.setBusSpeed(witnessAndOtherDto.getBusSpeed());
		existingWitnessAndOther.setIsHorned(witnessAndOtherDto.getIsHorned());
		existingWitnessAndOther.setIsBeaked(witnessAndOtherDto.getIsBeaked());
		existingWitnessAndOther.setTryToStopIncident(witnessAndOtherDto.getTryToStopIncident());
		existingWitnessAndOther.setCulprit(witnessAndOtherDto.getCulprit());
		existingWitnessAndOther.setDescription(witnessAndOtherDto.getDescription());
		existingWitnessAndOther.setDate(witnessAndOtherDto.getDate());
		existingWitnessAndOther.setTime(witnessAndOtherDto.getTime());
		existingWitnessAndOther.setWitnessType(witnessAndOtherDto.getWitnessType());

		// Keep the existing incidentId unchanged
		existingWitnessAndOther.setIncident(associatedIncident);

		// Save the updated WitnessAndOther entity
		WitnessAndOther updatedWitnessAndOther = witnessAndOtherRepository.save(existingWitnessAndOther);

		// Convert the updated WitnessAndOther entity to DTO and return
		return toDto(updatedWitnessAndOther);
	}

	@Override
	public void deleteWitness(Integer id) {
		WitnessAndOther witness = witnessAndOtherRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("WitnessAndOther", "id", id));

		witnessAndOtherRepository.delete(witness);
	}

	@Override
	public List<WitnessAndOtherDto> getAllWitnessesSortedByType() {

		List<WitnessAndOther> witnesses = witnessAndOtherRepository.findAll();

		List<WitnessAndOther> sortedWitnesses = witnesses.stream()
				.sorted((w1, w2) -> w1.getWitnessType().compareTo(w2.getWitnessType())).collect(Collectors.toList());
		return sortedWitnesses.stream().map(this::toDto).collect(Collectors.toList());
	}
	
	
	public List<WitnessAndOtherDto> getWitnessAndOthersByPrefix(String prefix) {
        List<WitnessAndOther> witnessesAndOthers = witnessAndOtherRepository.findAll();

        String witnessType = prefix.equals("W-") ? "W" : (prefix.equals("O-") ? "O" : null);
        if (witnessType == null) {
            throw new IllegalArgumentException("Invalid prefix. Use 'W-' for Witness or 'O-' for Original.");
        }

        return witnessesAndOthers.stream()
                .filter(w -> w.getWitnessType().equals(witnessType))
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
