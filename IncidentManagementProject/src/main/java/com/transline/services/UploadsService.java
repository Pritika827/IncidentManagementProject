package com.transline.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.transline.dtos.UploadsDto;

public interface UploadsService {

	UploadsDto saveUploadFile(UploadsDto uploadsDto, MultipartFile file);

	UploadsDto getUploadFileById(int id);

	List<UploadsDto> getAllUploadFile();

	void deleteUploadById(int uploadId);

	UploadsDto updateUploadFile(int attachmId, UploadsDto uploadsDto, MultipartFile file);

}
