package com.transline.servicesImp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transline.dtos.InsuranceDto;
import com.transline.entities.Incidents;
import com.transline.entities.Insurance;
import com.transline.exceptions.ResourceNotFoundException;
import com.transline.repositories.IncidentRepository;
import com.transline.repositories.InsuranceRepository;
import com.transline.services.InsuranceService;

@Service
public class InsuranceServiceImpl implements InsuranceService {

	private static final Logger logger = LoggerFactory.getLogger(PoliceRemarksServiceImpl.class);

	@Autowired
	private InsuranceRepository insuranceRepository;

	@Autowired
	private IncidentRepository incidentRepository;

// ------------------MODEL MAPPER-----------------------------------------

	@Autowired
	private ModelMapper modelMapper;

	public Insurance toEntity(InsuranceDto dto) {
		Insurance insurance = this.modelMapper.map(dto, Insurance.class);
		return insurance;
	}

	public InsuranceDto toDTO(Insurance insurance) {
		if (insurance == null) {
			return null;
		}
		InsuranceDto dto = new InsuranceDto();
		dto.setId(insurance.getId());
		dto.setIncidentId(insurance.getIncident().getIncidentId());
		dto.setBusInsuranceCompanyName(insurance.getBusInsuranceCompanyName());
		dto.setBusPolicyNo(insurance.getBusPolicyNo());

		if (insurance.getOtherVehicle() != null) {
			InsuranceDto.OtherVehicleDTO dto2 = new InsuranceDto.OtherVehicleDTO();
			dto2.setVehicleNo(insurance.getOtherVehicle().getVehicleNo());
			dto2.setManufactureName(insurance.getOtherVehicle().getManufactureName());
			dto2.setLossDescription(insurance.getOtherVehicle().getLossDescription());
			dto2.setOwnerName(insurance.getOtherVehicle().getOwnerName());
			dto2.setOwnerAddress(insurance.getOtherVehicle().getOwnerAddress());
			dto2.setDriverName(insurance.getOtherVehicle().getDriverName());
			dto2.setDriverAddress(insurance.getOtherVehicle().getDriverAddress());
			dto2.setInsuranceCompanyName(insurance.getOtherVehicle().getInsuranceCompanyName());
			dto2.setInsuranceAddress(insurance.getOtherVehicle().getInsuranceAddress());
			dto.setOtherVehicle(dto2);
		}
		dto.setBusLossDescription(insurance.getBusLossDescription());
		dto.setBusOwner(insurance.getBusOwner());
		dto.setBusAddress(insurance.getBusAddress());
		dto.setIsAnimalInvolvement(insurance.getIsAnimalInvolvement());
		dto.setThirdPartyCompensation(insurance.getThirdPartyCompensation());
		return dto;
	}

//--------------------------------------------------------------------------------------------------------------------

	private void validateIncidentId(String incidentId) {
		boolean exists = incidentRepository.existsById(incidentId);
		if (!exists) {
			throw new RuntimeException("Incident with id " + incidentId + " does not exist.");
		}
	}

	@Override
	public InsuranceDto saveInsurance(InsuranceDto insuranceDto) {
		validateIncidentId(insuranceDto.getIncidentId());
		Insurance insurance = this.toEntity(insuranceDto);
		Insurance savedInsurance = insuranceRepository.save(insurance);
		return this.toDTO(savedInsurance);
	}

	public InsuranceDto getInsuranceById(Integer id) {
		Insurance insurance = this.insuranceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("insurance ", "id", +id));
		return this.toDTO(insurance);
	}

	@Override
	public List<InsuranceDto> getAllPoliceRemarks() {
		List<Insurance> dtos = this.insuranceRepository.findAll();
		List<InsuranceDto> dtos2 = dtos.stream().map(dto -> this.toDTO(dto)).collect(Collectors.toList());
		return dtos2;
	}

	@Override
	public void deleteInsuranceDTO(Integer id) {
		Insurance insurance = this.insuranceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("insurance", "id", +id));
		this.insuranceRepository.delete(insurance);
	}

	@Override
	public InsuranceDto updateInsurance(InsuranceDto insuranceDto, Integer id) {
		// Find the existing Insurance entity by ID
		Insurance existingInsurance = insuranceRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Insurance", "id", id));

		// Find the associated Incident entity from the existing Insurance record
		Incidents associatedIncident = existingInsurance.getIncident();
		if (!associatedIncident.getIncidentId().equals(insuranceDto.getIncidentId())) {
			throw new IllegalArgumentException("Provided incidentId does not match the existing record.");
		}

		// Update the Insurance entity with new data, but keep the existing Incident
		existingInsurance.setBusInsuranceCompanyName(insuranceDto.getBusInsuranceCompanyName());
		existingInsurance.setBusPolicyNo(insuranceDto.getBusPolicyNo());

		if (insuranceDto.getOtherVehicle() != null) {
			Insurance.OtherVehicleDetails otherVehicleDetails = new Insurance.OtherVehicleDetails();
			otherVehicleDetails.setVehicleNo(insuranceDto.getOtherVehicle().getVehicleNo());
			otherVehicleDetails.setManufactureName(insuranceDto.getOtherVehicle().getManufactureName());
			otherVehicleDetails.setLossDescription(insuranceDto.getOtherVehicle().getLossDescription());
			otherVehicleDetails.setOwnerName(insuranceDto.getOtherVehicle().getOwnerName());
			otherVehicleDetails.setOwnerAddress(insuranceDto.getOtherVehicle().getOwnerAddress());
			otherVehicleDetails.setDriverName(insuranceDto.getOtherVehicle().getDriverName());
			otherVehicleDetails.setDriverAddress(insuranceDto.getOtherVehicle().getDriverAddress());
			otherVehicleDetails.setInsuranceCompanyName(insuranceDto.getOtherVehicle().getInsuranceCompanyName());
			otherVehicleDetails.setInsuranceAddress(insuranceDto.getOtherVehicle().getInsuranceAddress());
			existingInsurance.setOtherVehicle(otherVehicleDetails);
		}

		existingInsurance.setBusLossDescription(insuranceDto.getBusLossDescription());
		existingInsurance.setBusOwner(insuranceDto.getBusOwner());
		existingInsurance.setBusAddress(insuranceDto.getBusAddress());
		existingInsurance.setIsAnimalInvolvement(insuranceDto.getIsAnimalInvolvement());
		existingInsurance.setThirdPartyCompensation(insuranceDto.getThirdPartyCompensation());

		// Keep the existing incidentId unchanged
		existingInsurance.setIncident(associatedIncident);

		// Save the updated Insurance entity
		Insurance updatedInsurance = insuranceRepository.save(existingInsurance);

		// Convert the updated Insurance entity to DTO and return
		return toDTO(updatedInsurance);
	}

	@Override
	public InsuranceDto getInsuranceByIncidentId(String incidentId) {
		Insurance insurance = insuranceRepository.findByIncidentId(incidentId);
		if (insurance == null) {
			throw new ResourceNotFoundException("Insurance", "incidnet id", incidentId);
		}
		return this.toDTO(insurance);
	}

}
