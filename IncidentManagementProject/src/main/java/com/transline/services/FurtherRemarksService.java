package com.transline.services;

import java.util.List;

import com.transline.dtos.FurtherRemarksDto;

public interface FurtherRemarksService {

	FurtherRemarksDto saveFurtherRemarks(FurtherRemarksDto remarksDto);

	List<FurtherRemarksDto> getAllFurtherRemarks();

	FurtherRemarksDto getFurtherRemarksById(Integer id);

	FurtherRemarksDto updateFurtherRemarksDto(FurtherRemarksDto remarksDto, Integer id);

	void deleteFurtherRemarks(Integer id);

	List<FurtherRemarksDto> getAllFutherRemarksByIncidentId(String remarksRepository);
}
