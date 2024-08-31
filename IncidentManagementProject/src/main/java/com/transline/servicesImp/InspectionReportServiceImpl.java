package com.transline.servicesImp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transline.dtos.InspectionReportDto;
import com.transline.dtos.InsuranceDto;
import com.transline.entities.FurtherRemarks;
import com.transline.entities.Incidents;
import com.transline.entities.InspectionReport;
import com.transline.entities.Insurance;
import com.transline.exceptions.ResourceNotFoundException;
import com.transline.repositories.IncidentRepository;
import com.transline.repositories.InspectionReportRepository;
import com.transline.services.InspectionReportService;

@Service
public class InspectionReportServiceImpl implements InspectionReportService {

	@Autowired
	private InspectionReportRepository inspectionReportRepository;

	@Autowired
	private IncidentRepository incidentRepository;

// -----------------Model Mapper--------------------------------------------------------------
	@Autowired
	private ModelMapper modelMapper;

	private InspectionReport dtoToInspectionReport(InspectionReportDto reportDto) {
		InspectionReport report = this.modelMapper.map(reportDto, InspectionReport.class);
		return report;

//	    InspectionReport report = new InspectionReport();
//	    report.setId(reportDto.getId());
//	     
//	    Incidents incident = incidentRepository.findById(reportDto.getIncidentId())
//	            .orElseThrow(() -> new ResourceNotFoundException("Incident", "id", reportDto.getIncidentId())); 
//	 //   report.setIncident(incident.getIncidentId());
//	    report.setAccidentDescription(reportDto.getAccidentDescription());
//	    report.setTimeTakenToReach(reportDto.getTimeTakenToReach());
//	    report.setIncidentReportedBy(reportDto.getIncidentReportedBy());
//	    report.setTimeToIncidentReport(reportDto.getTimeToIncidentReport());
//	    report.setReasonOfAccident(reportDto.getReasonOfAccident());
//	    report.setRoadLightCondition(reportDto.getRoadLightCondition());
//	    report.setDistanceToBusStop(reportDto.getDistanceToBusStop());
//	    report.setCulprit(reportDto.isCulprit());
//	    report.setBusPartDamaged(reportDto.getBusPartDamaged());
//	    report.setRemarks(reportDto.getRemarks());
//	    report.setWorkShopDateTime(reportDto.getWorkShopDateTime());
//	    report.setPartsRepaired(reportDto.getPartsRepaired());
//	    report.setRepairingCost(reportDto.getRepairingCost());
//	    report.setPartCost(reportDto.getPartCost());
//	    report.setLabourerCost(reportDto.getLabourerCost());
//	    report.setOtherCost(reportDto.getOtherCost());
//	    report.setJobCartNo(reportDto.getJobCartNo());
//	    report.setRepairingDate(reportDto.getRepairingDate());
//	    report.setPresent(reportDto.isPresent());
//	    report.setBeaked(reportDto.isBeaked());    
//	    return report;
	}

	public InspectionReportDto inspectionReportToDto(InspectionReport report) {
		InspectionReportDto dto = new InspectionReportDto();
		dto.setId(report.getId());
		dto.setIncidentId(report.getIncident().getIncidentId());
		dto.setAccidentDescription(report.getAccidentDescription());
		dto.setTimeTakenToReach(report.getTimeTakenToReach());
		dto.setIncidentReportedBy(report.getIncidentReportedBy());
		dto.setTimeToIncidentReport(report.getTimeToIncidentReport());
		dto.setReasonOfAccident(report.getReasonOfAccident());
		dto.setRoadLightCondition(report.getRoadLightCondition());
		dto.setDistanceToBusStop(report.getDistanceToBusStop());
		dto.setCulprit(report.getCulprit());
		dto.setBusPartDamaged(report.getBusPartDamaged());
		dto.setRemarks(report.getRemarks());
		dto.setWorkShopDateTime(report.getWorkShopDateTime());
		dto.setPartsRepaired(report.getPartsRepaired());
		dto.setRepairingCost(report.getRepairingCost());
		dto.setPartCost(report.getPartCost());
		dto.setLabourerCost(report.getLabourerCost());
		dto.setOtherCost(report.getOtherCost());
		dto.setJobCartNo(report.getJobCartNo());
		dto.setRepairingDate(report.getRepairingDate());
		dto.setPresent(report.isPresent());
		dto.setBeaked(report.isBeaked());
		return dto;
	}

	// ---------------------------------------------------------------------------------------------

	@Override
	public InspectionReportDto saveInspectionReport(InspectionReportDto inspectionReportDto) {
		InspectionReport inspectionReport = this.dtoToInspectionReport(inspectionReportDto);
		InspectionReport savedInspectionReport = this.inspectionReportRepository.save(inspectionReport);
		return this.inspectionReportToDto(savedInspectionReport);
	}

	@Override
	public List<InspectionReportDto> getAllInspectionReportDetails() {
		List<InspectionReport> reports = this.inspectionReportRepository.findAll();
		List<InspectionReportDto> reportDtos = reports.stream().map(report -> this.inspectionReportToDto(report))
				.collect(Collectors.toList());
		return reportDtos;
	}

	@Override
	public InspectionReportDto getInspectionReportById(Integer id) {
		InspectionReport inspectionReport = this.inspectionReportRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("inspection report", "id", "id"));
		return this.inspectionReportToDto(inspectionReport);
	}

	@Override
	public InspectionReportDto updateInspectionReportDto(InspectionReportDto inspectionReportDto, Integer id) {
		InspectionReport inspectionReport = this.inspectionReportRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("inspection report", "id", +id));

//		 Incidents incidents = incidentRepository.findById(inspectionReportDto.getIncidentId())
//		            .orElseThrow(() -> new ResourceNotFoundException("Incident", "id", inspectionReportDto.getIncidentId()));
//		   System.out.print("service"+id); 

		Incidents associatedIncident = inspectionReport.getIncident();
		if (!associatedIncident.getIncidentId().equals(inspectionReportDto.getIncidentId())) {
			throw new IllegalArgumentException("Provided incidentId does not match the existing record.");
		}

		inspectionReport.setId(inspectionReportDto.getId());
		inspectionReport.setAccidentDescription(inspectionReportDto.getAccidentDescription());
		inspectionReport.setBusPartDamaged(inspectionReportDto.getBusPartDamaged());
		inspectionReport.setCulprit(inspectionReportDto.getCulprit());
		inspectionReport.setDistanceToBusStop(inspectionReportDto.getDistanceToBusStop());
		inspectionReport.setIncidentReportedBy(inspectionReportDto.getIncidentReportedBy());
		inspectionReport.setBeaked(inspectionReportDto.isBeaked());
		inspectionReport.setPresent(inspectionReportDto.isPresent());
		inspectionReport.setJobCartNo(inspectionReportDto.getJobCartNo());
		inspectionReport.setLabourerCost(inspectionReportDto.getLabourerCost());
		inspectionReport.setOtherCost(inspectionReportDto.getOtherCost());
		inspectionReport.setPartCost(inspectionReportDto.getPartCost());
		inspectionReport.setPartsRepaired(inspectionReportDto.getPartsRepaired());
		inspectionReport.setReasonOfAccident(inspectionReportDto.getReasonOfAccident());
		inspectionReport.setRemarks(inspectionReportDto.getRemarks());
		inspectionReport.setRemarks(inspectionReportDto.getRemarks());
		inspectionReport.setRepairingCost(inspectionReportDto.getRepairingCost());
		inspectionReport.setRepairingDate(inspectionReportDto.getRepairingDate());
		inspectionReport.setRoadLightCondition(inspectionReportDto.getRoadLightCondition());
		inspectionReport.setTimeTakenToReach(inspectionReportDto.getTimeTakenToReach());
		inspectionReport.setTimeToIncidentReport(inspectionReportDto.getTimeToIncidentReport());
		inspectionReport.setWorkShopDateTime(inspectionReportDto.getWorkShopDateTime());
		inspectionReport.setIncident(associatedIncident);

		InspectionReport updatedInspectionReport = this.inspectionReportRepository.save(inspectionReport);
		InspectionReportDto inspectionReportDto2 = this.inspectionReportToDto(updatedInspectionReport);
		return inspectionReportDto2;
	}

	@Override
	public void deleteInspectionReport(Integer id) {
		InspectionReport inspectionReport = this.inspectionReportRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("inspection report", "id", "id"));
		this.inspectionReportRepository.delete(inspectionReport);
	}

	@Override
	public InspectionReportDto getInspectionByIncidentId(String incidentId) {
		InspectionReport inspectionReport = inspectionReportRepository.findByIncidentId(incidentId);
		if (inspectionReport == null) {
			throw new ResourceNotFoundException("Inspection Report", "incident id", incidentId);
		}
		return this.inspectionReportToDto(inspectionReport);
	}

}
