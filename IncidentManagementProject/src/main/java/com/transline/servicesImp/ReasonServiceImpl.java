package com.transline.servicesImp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transline.dtos.InsuranceDto;
import com.transline.dtos.ReasonDto;
import com.transline.entities.Incidents;
import com.transline.entities.Insurance;
import com.transline.entities.Reason;
import com.transline.exceptions.ResourceNotFoundException;
import com.transline.repositories.IncidentRepository;
import com.transline.repositories.ReasonRepository;
import com.transline.services.ReasonService;

@Service
public class ReasonServiceImpl implements ReasonService {

	@Autowired
	private ReasonRepository reasonRepository;

	@Autowired
	private IncidentRepository incidentRepository;

// -----------------Model Mapper--------------------------------------------------------------
	@Autowired
	private ModelMapper modelMapper;

	private Reason toEntity(ReasonDto reasonDto) {
		Reason reason = this.modelMapper.map(reasonDto, Reason.class);
		return reason;
	}

	public ReasonDto toDto(Reason reason) {
//		ReasonDTO reasonDTO=this.modelMapper.map(reason, ReasonDTO.class);
//		reasonDTO.setId(reason.getId());
//		return reasonDTO;
		
		ReasonDto reasonDto = new ReasonDto();
		reasonDto.setId(reason.getId());
		reasonDto.setIncidentId(reason.getIncident().getIncidentId());
		reasonDto.setBusDirection(reason.getBusDirection());
		reasonDto.setOtherVehicleDirection(reason.getOtherVehicleDirection());
		reasonDto.setBusSpeed(reason.getBusSpeed());
		reasonDto.setOtherVehicleSpeed(reason.getOtherVehicleSpeed());
		reasonDto.setIsHorned(reason.getIsHorned());
		reasonDto.setIsBeaked(reason.getIsBeaked());
		reasonDto.setAtmosphericDesc(reason.getAtmosphericDesc());
		reasonDto.setRoadCondition(reason.getRoadCondition());
		reasonDto.setRoadWidth(reason.getRoadWidth());

		if (reason.getBusLight() != null) {
			ReasonDto.BusLightDTO busLightDTO = new ReasonDto.BusLightDTO();
			busLightDTO.setHeadLight(reason.getBusLight().getHeadLight());
			busLightDTO.setTailLight(reason.getBusLight().getTailLight());
			busLightDTO.setInteriorLight(reason.getBusLight().getInteriorLight());
			busLightDTO.setBrakeLight(reason.getBusLight().getBrakeLight());
			reasonDto.setBusLight(busLightDTO);
		}

		reasonDto.setVehicleLightDesc1(reason.getVehicleLightDesc1());
		reasonDto.setVehicleLightDesc2(reason.getVehicleLightDesc2());
		reasonDto.setRoadLightCondition(reason.getRoadLightCondition());
		reasonDto.setIsVehicleInLane(reason.getIsVehicleInLane());
		reasonDto.setVehicleLaneDesc(reason.getVehicleLaneDesc());
		reasonDto.setBusStopDistance(reason.getBusStopDistance());
		reasonDto.setTrafficSignalDistance(reason.getTrafficSignalDistance());
		reasonDto.setBusSpeedAccordingGPS(reason.getBusSpeedAccordingGPS());
		reasonDto.setAnalysisOfAccident(reason.getAnalysisOfAccident());
		reasonDto.setAccidentCausedByPassenger(reason.getAccidentCausedByPassenger());
		reasonDto.setAccidentCausedByVehicle(reason.getAccidentCausedByVehicle());

		return reasonDto;
	}

// ---------------------------------------------------------------------------------------------

	private void validateIncidentId(String incidentId) {
		boolean exists = incidentRepository.existsById(incidentId);
		if (!exists) {
			throw new RuntimeException("Incident with id " + incidentId + " does not exist.");
		}
	}

	@Override
	public ReasonDto saveReason(ReasonDto reasonDto) {
		validateIncidentId(reasonDto.getIncidentId());
		Reason reason = this.toEntity(reasonDto);
		Reason savedReason = reasonRepository.save(reason);
		return this.toDto(savedReason);
	}

	@Override
	public ReasonDto getReasonById(Integer id) {
		Reason reason = this.reasonRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Reason", "id", +id));
		return this.toDto(reason);
	}
	
	@Override
    public ReasonDto getReasonByIncidentId(String incidentId) {
        Reason reason = reasonRepository.findByIncidentId(incidentId);
        if (reason == null) {
            throw new ResourceNotFoundException("Reason", "incidentId", incidentId);
        }
        return this.toDto(reason);
    }

	@Override
	public List<ReasonDto> getAllReasons() {
		List<Reason> reasons = this.reasonRepository.findAll();
		List<ReasonDto> dtos = reasons.stream().map(dto -> this.toDto(dto)).collect(Collectors.toList());
		return dtos;
	}

//	@Override
//	public ReasonDTO updateReason(Integer id, ReasonDTO reasonDTO) {
//		Reason existingReason = this.reasonRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Reason", "id", +id));
//		
//		Reason updatedReason = this.reasonRepository.save(existingReason);
//		ReasonDTO reasonDTO2 = this.toDto(updatedReason);
//		return reasonDTO2;	
//	}

	  public ReasonDto updateReason(ReasonDto reasonDto, Integer id) {
	        // Fetch the existing Reason entity by its ID
	        Reason existingReason = reasonRepository.findById(reasonDto.getId())
	                .orElseThrow(() -> new ResourceNotFoundException("Reason", "id", reasonDto.getId()));

	        // Validate that the provided incidentId matches the existing Incident
	        Incidents existingIncident = existingReason.getIncident();
	        if (!existingIncident.getIncidentId().equals(reasonDto.getIncidentId())) {
	            throw new IllegalArgumentException("Provided incidentId does not match the existing record.");
	        }

	        // Update the existing Reason entity with new values
	        existingReason.setBusDirection(reasonDto.getBusDirection());
	        existingReason.setOtherVehicleDirection(reasonDto.getOtherVehicleDirection());
	        existingReason.setBusSpeed(reasonDto.getBusSpeed());
	        existingReason.setOtherVehicleSpeed(reasonDto.getOtherVehicleSpeed());
	        existingReason.setIsHorned(reasonDto.getIsHorned());
	        existingReason.setIsBeaked(reasonDto.getIsBeaked());
	        existingReason.setAtmosphericDesc(reasonDto.getAtmosphericDesc());
	        existingReason.setRoadCondition(reasonDto.getRoadCondition());
	        existingReason.setRoadWidth(reasonDto.getRoadWidth());

	        // Handle BusLight
	        if (reasonDto.getBusLight() != null) {
	            Reason.BusLight busLight = new Reason.BusLight();
	            busLight.setHeadLight(reasonDto.getBusLight().getHeadLight());
	            busLight.setTailLight(reasonDto.getBusLight().getTailLight());
	            busLight.setInteriorLight(reasonDto.getBusLight().getInteriorLight());
	            busLight.setBrakeLight(reasonDto.getBusLight().getBrakeLight());
	            existingReason.setBusLight(busLight);
	        }

	        existingReason.setVehicleLightDesc1(reasonDto.getVehicleLightDesc1());
	        existingReason.setVehicleLightDesc2(reasonDto.getVehicleLightDesc2());
	        existingReason.setRoadLightCondition(reasonDto.getRoadLightCondition());
	        existingReason.setIsVehicleInLane(reasonDto.getIsVehicleInLane());
	        existingReason.setVehicleLaneDesc(reasonDto.getVehicleLaneDesc());
	        existingReason.setBusStopDistance(reasonDto.getBusStopDistance());
	        existingReason.setTrafficSignalDistance(reasonDto.getTrafficSignalDistance());
	        existingReason.setBusSpeedAccordingGPS(reasonDto.getBusSpeedAccordingGPS());
	        existingReason.setAnalysisOfAccident(reasonDto.getAnalysisOfAccident());
	        existingReason.setAccidentCausedByVehicle(reasonDto.getAccidentCausedByVehicle());
	        existingReason.setAccidentCausedByPassenger(reasonDto.getAccidentCausedByPassenger());

	        // Save and return the updated Reason entity
	        Reason updatedReason = reasonRepository.save(existingReason);
	        return toDto(updatedReason);
	    }
	
	
	/*@Override
	public InsuranceDTO updateInsurance(InsuranceDTO insuranceDTO, Integer id) {
		// Find the existing Insurance entity by ID
		Insurance existingInsurance = insuranceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Insurance", "id", id));

		// Find the associated Incident entity from the existing Insurance record
		Incidents associatedIncident = existingInsurance.getIncident();
		if (!associatedIncident.getIncidentId().equals(insuranceDTO.getIncidentId())) {
			throw new IllegalArgumentException("Provided incidentId does not match the existing record.");
		}

		// Update the Insurance entity with new data, but keep the existing Incident
		existingInsurance.setBusInsuranceCompanyName(insuranceDTO.getBusInsuranceCompanyName());
		existingInsurance.setBusPolicyNo(insuranceDTO.getBusPolicyNo());

		if (insuranceDTO.getOtherVehicle() != null) {
			Insurance.OtherVehicleDetails otherVehicleDetails = new Insurance.OtherVehicleDetails();
			otherVehicleDetails.setVehicleNo(insuranceDTO.getOtherVehicle().getVehicleNo());
			otherVehicleDetails.setManufactureName(insuranceDTO.getOtherVehicle().getManufactureName());
			otherVehicleDetails.setLossDescription(insuranceDTO.getOtherVehicle().getLossDescription());
			otherVehicleDetails.setOwnerName(insuranceDTO.getOtherVehicle().getOwnerName());
			otherVehicleDetails.setOwnerAddress(insuranceDTO.getOtherVehicle().getOwnerAddress());
			otherVehicleDetails.setDriverName(insuranceDTO.getOtherVehicle().getDriverName());
			otherVehicleDetails.setDriverAddress(insuranceDTO.getOtherVehicle().getDriverAddress());
			otherVehicleDetails.setInsuranceCompanyName(insuranceDTO.getOtherVehicle().getInsuranceCompanyName());
			otherVehicleDetails.setInsuranceAddress(insuranceDTO.getOtherVehicle().getInsuranceAddress());
			existingInsurance.setOtherVehicle(otherVehicleDetails);
		}

		existingInsurance.setBusLossDescription(insuranceDTO.getBusLossDescription());
		existingInsurance.setBusOwner(insuranceDTO.getBusOwner());
		existingInsurance.setBusAddress(insuranceDTO.getBusAddress());
		existingInsurance.setIsAnimalInvolvement(insuranceDTO.getIsAnimalInvolvement());
		existingInsurance.setThirdPartyCompensation(insuranceDTO.getThirdPartyCompensation());

		// Keep the existing incidentId unchanged
		existingInsurance.setIncident(associatedIncident);

		// Save the updated Insurance entity
		Insurance updatedInsurance = insuranceRepository.save(existingInsurance);

		// Convert the updated Insurance entity to DTO and return
		return toDTO(updatedInsurance);
	}*/
	@Override
	public void deleteReason(Integer id) {
		Reason reason = this.reasonRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Reason", "id", +id));
		this.reasonRepository.delete(reason);
	}

	

}
