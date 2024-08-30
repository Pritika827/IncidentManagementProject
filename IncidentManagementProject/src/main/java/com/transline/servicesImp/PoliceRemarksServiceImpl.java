package com.transline.servicesImp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transline.dtos.PoliceRemarksDto;
import com.transline.entities.Incidents;
import com.transline.entities.PoliceRemarks;
import com.transline.exceptions.ResourceNotFoundException;
import com.transline.repositories.IncidentRepository;
import com.transline.repositories.PoliceRemarksRepository;
import com.transline.services.PoliceRemarksService;

@Service
public class PoliceRemarksServiceImpl implements PoliceRemarksService {

	@Autowired
	private PoliceRemarksRepository policeRemarksRepository;

//	@Autowired
//	private PoliceRemarksMapper policeRemarksMapper;

	// ------------------MODEL MAPPER-----------------------------------------
	@Autowired
	private IncidentRepository incidentRepository;

	@Autowired
	private ModelMapper modelMapper;

	private static final Logger logger = LoggerFactory.getLogger(PoliceRemarksServiceImpl.class);

//	public static Incidents convertIncidentToString(String incidentId) {
//	    return new Incidents(incidentId); // Adjust this to your actual constructor or method
//	}

	public PoliceRemarksDto toDTO(PoliceRemarks policeRemarks) {
		if (policeRemarks == null) {
			return null;
		}

		PoliceRemarksDto dto = new PoliceRemarksDto();
		dto.setId(policeRemarks.getId());
		dto.setIncidentId(policeRemarks.getIncident().getIncidentId());
		// dto.setIncident(convertIncidentToString(policeRemarks.getIncident().getIncidentId()));
		// dto.setIncidentId(policeRemarks.getIncident());
		dto.setName(policeRemarks.getName());
		dto.setLocation(policeRemarks.getLocation());
		dto.setPoliceStationName(policeRemarks.getPoliceStationName());
		dto.setIsIncidentSeen(policeRemarks.getIsIncidentSeen());
		dto.setDescription(policeRemarks.getDescription());

		if (policeRemarks.getInvestigationReport() != null) {
			PoliceRemarksDto.InvestigationReportDTO reportDTO = new PoliceRemarksDto.InvestigationReportDTO();
			reportDTO.setReportName(policeRemarks.getInvestigationReport().getReportName());
			reportDTO.setAddress(policeRemarks.getInvestigationReport().getAddress());
			reportDTO.setInjuredDescription(policeRemarks.getInvestigationReport().getInjuredDescription());
			reportDTO.setRemarks(policeRemarks.getInvestigationReport().getRemarks());
			dto.setInvestigationReport(reportDTO);
		}

		dto.setDrName(policeRemarks.getDrName());
		dto.setDrAddress(policeRemarks.getDrAddress());
		dto.setHospitalName(policeRemarks.getHospitalName());
		dto.setReferenceNo(policeRemarks.getReferenceNo());

		return dto;
	}

//	public static Incidents convertStringToIncident(String incidentId) {
//	    return new Incidents(incidentId); // Adjust this to your actual constructor or method
//	}

	public PoliceRemarks toEntity(PoliceRemarksDto dto) {

		PoliceRemarks policeRemarks = this.modelMapper.map(dto, PoliceRemarks.class);
		return policeRemarks;
	}

//	if (dto == null) {
//	return null;
//}
//
// PoliceRemarks policeRemarks = new PoliceRemarks();

// InspectionReport report=this.modelMapper.map(reportDto, InspectionReport.class);
//	return report;
//

//		 Incidents incidents = incidentRepository.findById(inspectionReportDto.getIncidentId())
//		            .orElseThrow(() -> new ResourceNotFoundException("Incident", "id", inspectionReportDto.getIncidentId()));
//		   System.out.print("service"+id); 
//		   

//		if (dto.getIncident() != null) {
//		policeRemarks.setIncident(convertStringToIncident(dto.getIncident().getIncidentId()));
//		}else {
//			 logger.warn("Incident is null in PoliceRemarksDTO");
//		}

	// policeRemarks.setIncident(dto.getIncidentId());

	// Convert String incidentId to Incident object
//		Incidents incident =Incidents(incidentService.findById(dto.getIncidentId()));
//		if (incident != null) {
//			policeRemarks.setIncident(incident);
//		} else {
//			// Handle the case where the Incident is not found
//			throw new IllegalArgumentException("Incident not found for ID: " + dto.getIncidentId());
//		}

	/*
	 * Incidents
	 * incidents=incidentRepository.findById(dto.getIncidentId()).orElseThrow( ()->
	 * new ResourceNotFoundException("policy remarks","id",dto.getIncidentId()));
	 */

//		String iid=(String)dto.getIncidentId();	
//		policeRemarks.setIncident(iid);

	/*
	 * policeRemarks.setIncident(incidents); policeRemarks.setName(dto.getName());
	 * policeRemarks.setLocation(dto.getLocation());
	 * policeRemarks.setPoliceStationName(dto.getPoliceStationName());
	 * policeRemarks.setIsIncidentSeen(dto.getIsIncidentSeen());
	 * policeRemarks.setDescription(dto.getDescription());
	 * 
	 * if (dto.getInvestigationReport() != null) { PoliceRemarks.InvestigationReport
	 * report = new PoliceRemarks.InvestigationReport();
	 * report.setReportName(dto.getInvestigationReport().getReportName());
	 * report.setAddress(dto.getInvestigationReport().getAddress());
	 * report.setInjuredDescription(dto.getInvestigationReport().
	 * getInjuredDescription());
	 * report.setRemarks(dto.getInvestigationReport().getRemarks());
	 * policeRemarks.setInvestigationReport(report); }
	 * 
	 * policeRemarks.setDrName(dto.getDrName());
	 * policeRemarks.setDrAddress(dto.getDrAddress());
	 * policeRemarks.setHospitalName(dto.getHospitalName());
	 * policeRemarks.setReferenceNo(dto.getReferenceNo());
	 * 
	 * return policeRemarks; }
	 */
	// -------------------------------------------------------------------------------------------------

	private void validateIncidentId(String incidentId) {
		boolean exists = incidentRepository.existsById(incidentId);
		if (!exists) {
			throw new RuntimeException("Incident with id " + incidentId + " does not exist.");
		}
	}

	@Override
	public PoliceRemarksDto savePoliceRemarks(PoliceRemarksDto policeRemarksDto) {
		validateIncidentId(policeRemarksDto.getIncidentId());
		PoliceRemarks policeRemarks = this.toEntity(policeRemarksDto);
		PoliceRemarks savedPoliceRemarks = policeRemarksRepository.save(policeRemarks);
		return this.toDTO(savedPoliceRemarks);
	}

	public PoliceRemarksDto getPoliceRemarksById(Integer id) {
		PoliceRemarks policeRemarks = this.policeRemarksRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("policy remarks", "id", +id));
		return this.toDTO(policeRemarks);
	}

	@Override
	public List<PoliceRemarksDto> getAllPoliceRemarks() {
		List<PoliceRemarks> dtos = this.policeRemarksRepository.findAll();
		List<PoliceRemarksDto> dtos2 = dtos.stream().map(dto -> this.toDTO(dto)).collect(Collectors.toList());
		return dtos2;
	}

	@Override
	public void deletePoliceRemarks(Integer id) {
		PoliceRemarks policeRemarks = this.policeRemarksRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("policy remakrs", "id", +id));
		this.policeRemarksRepository.delete(policeRemarks);
	}

//	@Override
//	public PoliceRemarksDTO updatePoliceRemarks(Integer id, PoliceRemarksDTO policeRemarksDTO) {
//		PoliceRemarks existingPoliceRemarks = this.policeRemarksRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("policy remarks", "id", +id));
//				
//		Incidents associatedIncident = existingPoliceRemarks.getIncident();
//		if (!associatedIncident.getIncidentId().equals(policeRemarksDTO.getIncidentId())) {
//			throw new IllegalArgumentException("Provided incidentId does not match the existing record.");
//		}
//		
//		PoliceRemarks updatedPolicy = this.policeRemarksRepository.save(existingPoliceRemarks);
//		PoliceRemarksDTO policeRemarksDTO2 = this.toDTO(updatedPolicy);
//		return policeRemarksDTO2;
//	}
	
	
	public PoliceRemarksDto updatePoliceRemarks(Integer id, PoliceRemarksDto policeRemarksDto) {
        // Find the existing PoliceRemarks record
        PoliceRemarks existingPoliceRemarks = policeRemarksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PoliceRemarks", "id", id));

        // Validate that the provided incidentId matches the existing Incident
        Incidents existingIncident = existingPoliceRemarks.getIncident();
        if (!existingIncident.getIncidentId().equals(policeRemarksDto.getIncidentId())) {
            throw new IllegalArgumentException("Provided incidentId does not match the existing record.");
        }

        // Update the PoliceRemarks record with the new values
        existingPoliceRemarks.setName(policeRemarksDto.getName());
        existingPoliceRemarks.setLocation(policeRemarksDto.getLocation());
        existingPoliceRemarks.setPoliceStationName(policeRemarksDto.getPoliceStationName());
        existingPoliceRemarks.setIsIncidentSeen(policeRemarksDto.getIsIncidentSeen());
        existingPoliceRemarks.setDescription(policeRemarksDto.getDescription());

        // Handle InvestigationReport
        if (policeRemarksDto.getInvestigationReport() != null) {
            PoliceRemarks.InvestigationReport report = new PoliceRemarks.InvestigationReport();
            report.setReportName(policeRemarksDto.getInvestigationReport().getReportName());
            report.setAddress(policeRemarksDto.getInvestigationReport().getAddress());
            report.setInjuredDescription(policeRemarksDto.getInvestigationReport().getInjuredDescription());
            report.setRemarks(policeRemarksDto.getInvestigationReport().getRemarks());
            existingPoliceRemarks.setInvestigationReport(report);
        }

        existingPoliceRemarks.setDrName(policeRemarksDto.getDrName());
        existingPoliceRemarks.setDrAddress(policeRemarksDto.getDrAddress());
        existingPoliceRemarks.setHospitalName(policeRemarksDto.getHospitalName());
        existingPoliceRemarks.setReferenceNo(policeRemarksDto.getReferenceNo());

        // Save and return the updated PoliceRemarks record
        PoliceRemarks updatedPoliceRemarks = policeRemarksRepository.save(existingPoliceRemarks);
        return toDTO(updatedPoliceRemarks);
    }

}
