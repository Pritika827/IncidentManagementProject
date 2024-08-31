package com.transline.servicesImp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transline.dtos.FurtherRemarksDto;
import com.transline.dtos.InspectionReportDto;
import com.transline.entities.FurtherRemarks;
import com.transline.entities.Incidents;
import com.transline.entities.InspectionReport;
import com.transline.exceptions.ResourceNotFoundException;
import com.transline.repositories.FurtherRemarksRepository;
import com.transline.services.FurtherRemarksService;

@Service
public class FurtherRemarksServiceImpl implements FurtherRemarksService {

	@Autowired
	private FurtherRemarksRepository remarksRepository;

// -----------------Model Mapper--------------------------------------------------------------
	@Autowired
	private ModelMapper modelMapper;

	private FurtherRemarks dtoToFurtherRemarks(FurtherRemarksDto remarksDto) {
		FurtherRemarks remarks = this.modelMapper.map(remarksDto, FurtherRemarks.class);
		return remarks;
	}

	public FurtherRemarksDto FurtherRemarksToDto(FurtherRemarks remarks) {
		FurtherRemarksDto remarksDto = new FurtherRemarksDto();
		remarksDto.setId(remarks.getId());
		remarksDto.setIncidentId(remarks.getIncident().getIncidentId());
		remarksDto.setDate(remarks.getDate());
		remarksDto.setDescription(remarks.getDescription());
		remarksDto.setReportedBy(remarks.getReportedBy());
		remarksDto.setTime(remarks.getTime());
		return remarksDto;
	}

//---------------------------------------------------------------------------------------------

	@Override
	public FurtherRemarksDto saveFurtherRemarks(FurtherRemarksDto remarksDto) {
		FurtherRemarks furtherRemarks = this.dtoToFurtherRemarks(remarksDto);
		FurtherRemarks savedFurthRemarks = this.remarksRepository.save(furtherRemarks);
		return this.FurtherRemarksToDto(savedFurthRemarks);
	}

	@Override
	public List<FurtherRemarksDto> getAllFurtherRemarks() {
		List<FurtherRemarks> remarks = this.remarksRepository.findAll();
		List<FurtherRemarksDto> remarksDtos = remarks.stream().map(remark -> this.FurtherRemarksToDto(remark))
				.collect(Collectors.toList());
		return remarksDtos;
	}

	@Override
	public FurtherRemarksDto getFurtherRemarksById(Integer id) {
		FurtherRemarks remarks = this.remarksRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("further remarks", "id", +id));
		return this.FurtherRemarksToDto(remarks);
	}

	@Override
	public FurtherRemarksDto updateFurtherRemarksDto(FurtherRemarksDto remarksDto, Integer id) {
		FurtherRemarks remarks = this.remarksRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("futher remarks", "id", +id));

		Incidents associatedIncident = remarks.getIncident();
		if (!associatedIncident.getIncidentId().equals(remarksDto.getIncidentId())) {
			throw new IllegalArgumentException("Provided incidentId does not match the existing record.");
		}

		remarks.setDate(remarksDto.getDate());
		remarks.setDescription(remarksDto.getDescription());
		remarks.setReportedBy(remarksDto.getReportedBy());
		remarks.setTime(remarksDto.getTime());

		FurtherRemarks updatedRemarks = this.remarksRepository.save(remarks);
		FurtherRemarksDto remarksDto2 = this.FurtherRemarksToDto(updatedRemarks);
		return remarksDto2;
	}

	@Override
	public void deleteFurtherRemarks(Integer id) {
		FurtherRemarks remarks = this.remarksRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("further remarks", "id", +id));
		this.remarksRepository.delete(remarks);
	}

//	@Override
//	public List<FurtherRemarksDto> getAllFutherRemarksByIncidentId(String incidentId) {
//		List<FurtherRemarks> furtherRemarksList = remarksRepository.findByIncidentIncidentId(incidentId);
//		return furtherRemarksList.stream().map(this::FurtherRemarksToDto).collect(Collectors.toList());
//	}

	@Override
	public FurtherRemarksDto getFutherRemarkByIncidentId(String incidentId) {
		FurtherRemarks furtherRemarks = remarksRepository.findByIncidentId(incidentId);
		if (furtherRemarks == null) {
			throw new ResourceNotFoundException("Further remarks", "incident id", incidentId);
		}
		return this.FurtherRemarksToDto(furtherRemarks);
	}

}
