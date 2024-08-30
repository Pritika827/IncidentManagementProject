package com.transline.servicesImp;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transline.dtos.IncidentsDto;
import com.transline.entities.CompanyMst;
import com.transline.entities.Incidents;
import com.transline.exceptions.ResourceNotFoundException;
import com.transline.repositories.CompanyMstRepository;
import com.transline.repositories.IncidentRepository;
import com.transline.services.IncidentServices;

import jakarta.transaction.Transactional;

@Service
public class IncidentServicesImpl implements IncidentServices {

	@Autowired
	private IncidentRepository incidentRepository;

	@Autowired
	private CompanyMstRepository companyMstRepository;

// -----------------Model Mapper--------------------------------------------------------------
	@Autowired
	private ModelMapper modelMapper;

//	private Incidents dtoToIncident(IncidentsDto incidentsDto) {
//		Incidents incidents = this.modelMapper.map(incidentsDto, Incidents.class);
//		return incidents;
//	}

	public Incidents dtoToIncident(IncidentsDto incidentsDto) {
		Incidents incidents = new Incidents();
		incidents.setIncidentId(incidentsDto.getIncidentId());

		// Fetch CompanyMst entity using cmpCd
		CompanyMst companyMst = companyMstRepository.findByCmpCd(incidentsDto.getCmpCd());

		if (companyMst != null) {
			incidents.setCompanyMst(companyMst);
		} else {
			throw new ResourceNotFoundException("CompanyMst", "cmpCd", incidentsDto.getCmpCd());
		}

		incidents.setRscDairyNo(incidentsDto.getRscDairyNo());
		incidents.setInformedBy(incidentsDto.getInformedBy());
		incidents.setAttendedBy(incidentsDto.getAttendedBy());
		incidents.setBusId(incidentsDto.getBusId());
		incidents.setAccidentType(incidentsDto.getAccidentType());
		incidents.setDepotId(incidentsDto.getDepotId());
		incidents.setNoOfPassenger(incidentsDto.getNoOfPassenger());
		incidents.setDriverId(incidentsDto.getDriverId());
		incidents.setConductorId(incidentsDto.getConductorId());
		incidents.setBusDamage(incidentsDto.isBusDamage());
		incidents.setDate(incidentsDto.getDate());
		incidents.setTime(incidentsDto.getTime());
		incidents.setRouteNo(incidentsDto.getRouteNo());
		incidents.setDutyNo(incidentsDto.getDutyNo());
		incidents.setLocationName(incidentsDto.getLocationName());
		incidents.setCity(incidentsDto.getCity());
		incidents.setPincode(incidentsDto.getPincode());
		incidents.setZone(incidentsDto.getZone());
		incidents.setAreaType(incidentsDto.getAreaType());
		incidents.setRegion(incidentsDto.getRegion());
		incidents.setDamagedDescription(incidentsDto.getDamagedDescription());
		incidents.setInjured(incidentsDto.getInjured());
		incidents.setDeath(incidentsDto.getDeath());

		return incidents;
	}

//	public IncidentsDto incidentToDto(Incidents incidents) {
//		IncidentsDto incidentsDto = this.modelMapper.map(incidents, IncidentsDto.class);
//		
////		IncidentsDto incidentsDto=new IncidentsDto();
////		incidentsDto.setCmp_cd(incidents.getCompanyMst().getCmpCd());
//		return incidentsDto;
//	}

	public IncidentsDto incidentToDto(Incidents incidents) {
		IncidentsDto incidentsDto = new IncidentsDto();
		incidentsDto.setIncidentId(incidents.getIncidentId());

		if (incidents.getCompanyMst() != null) {
			incidentsDto.setCmpCd(incidents.getCompanyMst().getCmpCd());
		}

		incidentsDto.setRscDairyNo(incidents.getRscDairyNo());
		incidentsDto.setInformedBy(incidentsDto.getInformedBy());
		incidentsDto.setAttendedBy(incidents.getAttendedBy());
		incidentsDto.setBusId(incidents.getBusId());
		incidentsDto.setAccidentType(incidents.getAccidentType());
		incidentsDto.setDepotId(incidents.getDepotId());
		incidentsDto.setNoOfPassenger(incidents.getNoOfPassenger());
		incidentsDto.setDriverId(incidents.getDriverId());
		incidentsDto.setConductorId(incidents.getConductorId());
		incidentsDto.setBusDamage(incidents.isBusDamage());
		incidentsDto.setDate(incidents.getDate());
		incidentsDto.setTime(incidents.getTime());
		incidentsDto.setRouteNo(incidents.getRouteNo());
		incidentsDto.setDutyNo(incidents.getDutyNo());
		incidentsDto.setLocationName(incidents.getLocationName());
		incidentsDto.setCity(incidents.getCity());
		incidentsDto.setPincode(incidents.getPincode());
		incidentsDto.setZone(incidents.getZone());
		incidentsDto.setAreaType(incidents.getAreaType());
		incidentsDto.setRegion(incidents.getRegion());
		incidentsDto.setDamagedDescription(incidents.getDamagedDescription());
		incidentsDto.setInjured(incidents.getInjured());
		incidentsDto.setDeath(incidents.getDeath());
		return incidentsDto;
	}

// ---------------------------------------------------------------------------------------------

//	@Transactional
//	@Override
//	public IncidentsDto saveIncidents(IncidentsDto incidentsDto) {		
//		Incidents incidents = this.dtoToIncident(incidentsDto);
//		int year = Year.now().getValue();
//		String idPrefix = "DTC" + year;
//		String prevId=incidentRepository.findMaxIncidentId(idPrefix);
//		
//		if (prevId == null) {
//			prevId = idPrefix + "00000";
//		}
//
//		// Now You got PrevId
//		int nextSeqNo = Integer.parseInt(prevId.substring(idPrefix.length())) + 1;
//		String newIncidentId=String.format("%s%05d", idPrefix, nextSeqNo);
//		incidents.setIncidentId(newIncidentId);
//		Incidents savedIncidents = this.incidentRepository.save(incidents);
//		return this.incidentToDto(savedIncidents);
//	}

	@Transactional
	@Override
	public IncidentsDto saveIncidents(IncidentsDto incidentsDto) {
		Incidents incidents = this.dtoToIncident(incidentsDto);

		CompanyMst companyMst = companyMstRepository.findByCmpCd(incidentsDto.getCmpCd());

		if (companyMst != null) {
			incidents.setCompanyMst(companyMst);
		} else {
			throw new ResourceNotFoundException("CompanyMst", "cmpCd", incidentsDto.getCmpCd());
		}

		int year = Year.now().getValue();
		String idPrefix = "DTC" + year;
		String prevId = incidentRepository.findMaxIncidentId(idPrefix);

		if (prevId == null) {
			prevId = idPrefix + "00000";
		}
		int nextSeqNo = Integer.parseInt(prevId.substring(idPrefix.length())) + 1;
		String newIncidentId = String.format("%s%05d", idPrefix, nextSeqNo);
		incidents.setIncidentId(newIncidentId);

		Incidents savedIncidents = this.incidentRepository.save(incidents);
		return this.incidentToDto(savedIncidents);
	}

	@Override
	public List<IncidentsDto> getAllIncidentsDetails() {
		List<Incidents> incidents = this.incidentRepository.findAll();
		List<IncidentsDto> incidentsDtos = incidents.stream().map(incident -> this.incidentToDto(incident))
				.collect(Collectors.toList());
		return incidentsDtos;
	}

	@Override
	public IncidentsDto getIncidentById(String incidentId) {
		Incidents incidents = this.incidentRepository.findById(incidentId)
				.orElseThrow(() -> new ResourceNotFoundException("incident", "id", "incidentId"));
		return this.incidentToDto(incidents);
	}

	@Override
	public IncidentsDto updateIncidentsDto(IncidentsDto incidentsDto, String incidentId) {
		Incidents incidents = this.incidentRepository.findById(incidentId)
				.orElseThrow(() -> new ResourceNotFoundException("incident", "id", incidentId));
		incidents.setRscDairyNo(incidentsDto.getRscDairyNo());
		incidents.setInformedBy(incidentsDto.getInformedBy());
		incidents.setAttendedBy(incidentsDto.getAttendedBy());
		incidents.setBusId(incidentsDto.getBusId());
		incidents.setAccidentType(incidentsDto.getAccidentType());
		incidents.setDepotId(incidentsDto.getDepotId());
		incidents.setNoOfPassenger(incidentsDto.getNoOfPassenger());
		incidents.setDriverId(incidentsDto.getDriverId());
		incidents.setConductorId(incidentsDto.getConductorId());
		incidents.setBusDamage(incidentsDto.isBusDamage());
		incidents.setDate(incidentsDto.getDate());
		incidents.setTime(incidentsDto.getTime());
		incidents.setRouteNo(incidentsDto.getRouteNo());
		incidents.setLocationName(incidentsDto.getLocationName());
		incidents.setCity(incidentsDto.getCity());
		incidents.setPincode(incidentsDto.getPincode());
		incidents.setZone(incidentsDto.getZone());
		incidents.setAreaType(incidentsDto.getAreaType());
		incidents.setRegion(incidentsDto.getRegion());
		incidents.setDamagedDescription(incidentsDto.getDamagedDescription());
		incidents.setInjured(incidentsDto.getInjured());
		incidents.setDeath(incidentsDto.getDeath());
		Incidents updatedIncident = this.incidentRepository.save(incidents);
		IncidentsDto incidentsDto2 = this.incidentToDto(updatedIncident);
		return incidentsDto2;
	}

	@Override
	public void deteleIncidents(String incidentsId) {
		Incidents incidents = this.incidentRepository.findById(incidentsId)
				.orElseThrow(() -> new ResourceNotFoundException("incidents", "id", incidentsId));
		this.incidentRepository.delete(incidents);
	}

	@Override
	public List<String> getAllIncidentIds() {
		return this.incidentRepository.getAllIncidentIds();
	}

}
