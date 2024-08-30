package com.transline.servicesImp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.transline.dtos.UploadsDto;
import com.transline.entities.Uploads;
import com.transline.exceptions.ResourceNotFoundException;
import com.transline.repositories.UploadsRepository;
import com.transline.services.UploadsService;
import com.transline.utils.ShortUniqueIdGenerator;

@Service
public class UploadsServiceImpl implements UploadsService {

	private static final String UPLOAD_DIR = "uploads/";

	@Autowired
	private UploadsRepository uploadsRepository;

//-------------------------------- Model Mapper-------------------------------------------------------

	@Autowired
	private ModelMapper modelMapper;

	// Method to convert Uploads entity to UploadsDto
	private UploadsDto toDto(Uploads uploads) {
		return modelMapper.map(uploads, UploadsDto.class);
	}

	// Method to convert UploadsDto to Uploads entity
	private Uploads toEntity(UploadsDto uploadsDto) {
		return modelMapper.map(uploadsDto, Uploads.class);
	}

//-----------------------------------------------------------------------------------------------------	 

	private String getFileExtension(String filename) {
		int dotIndex = filename.lastIndexOf(".");
		return dotIndex >= 0 ? filename.substring(dotIndex) : "";
	}

	@Override
	public UploadsDto saveUploadFile(UploadsDto uploadsDto, MultipartFile file) {
		if (file != null && !file.isEmpty()) {
			try {
				String shortUniqueId = ShortUniqueIdGenerator.generateShortUniqueId();
				String fileExtension = getFileExtension(file.getOriginalFilename());
				String fileName = shortUniqueId + fileExtension;

				Path filePath = Paths.get(UPLOAD_DIR + fileName);
				Files.createDirectories(filePath.getParent());
				Files.write(filePath, file.getBytes());

				uploadsDto.setFilePath(filePath.toString());
				uploadsDto.setFileSize(String.valueOf(file.getSize()));
				uploadsDto.setFileDescription("Uploaded file: " + file.getOriginalFilename());

			} catch (IOException e) {
				throw new RuntimeException("Failed to store file", e);
			}
		}

		Uploads uploadsEntity = toEntity(uploadsDto);

		System.out.println("Uploads Entity before save: " + uploadsEntity.toString());
		Uploads savedEntity = uploadsRepository.save(uploadsEntity);
		System.out.println("Uploads Entity after save: " + savedEntity.toString());

		return toDto(savedEntity);
	}

	@Override
	public UploadsDto getUploadFileById(int uploadId) {
		Uploads uploads = uploadsRepository.findById(uploadId)
				.orElseThrow(() -> new ResourceNotFoundException("upload file with id not found on the server" + uploadId));
		return this.toDto(uploads);
	}

	@Override
	public List<UploadsDto> getAllUploadFile() {
		List<Uploads> uploads = this.uploadsRepository.findAll();
		List<UploadsDto> uploadsDtos = uploads.stream().map(upload -> this.toDto(upload)).collect(Collectors.toList());
		return uploadsDtos;
	}



	@Override
	public UploadsDto updateUploadFile(int uploadId, UploadsDto uploadsDto, MultipartFile file) {
		// Retrieve the existing upload record by its ID
		Uploads existingUpload = this.uploadsRepository.findById(uploadId)
				.orElseThrow(() -> new ResourceNotFoundException("Upload", "id", uploadId));

		// Check if the refId matches the existing record's refId
		if (!existingUpload.getRefId().equals(uploadsDto.getRefId())) {
			throw new IllegalArgumentException("Provided refId does not match the existing record.");
		}

		// If a new file is uploaded, update the file path and other file details
		if (file != null && !file.isEmpty()) {
			try {
				// Generate a unique file name using ShortUniqueIdGenerator
				String shortUniqueId = ShortUniqueIdGenerator.generateShortUniqueId();
				String fileExtension = getFileExtension(file.getOriginalFilename());
				String fileName = shortUniqueId + fileExtension;

				// Define the file path for saving the uploaded file
				Path filePath = Paths.get(UPLOAD_DIR + fileName);

				// Create directories if they don't exist
				Files.createDirectories(filePath.getParent());

				// Save the file to the file system
				Files.write(filePath, file.getBytes());

				// Update the file-related information in the entity
				existingUpload.setFilePath(filePath.toString());
				existingUpload.setFileSize(String.valueOf(file.getSize()));
				existingUpload.setFileDescription("Uploaded file: " + file.getOriginalFilename());

			} catch (IOException e) {
				throw new RuntimeException("Failed to store file", e);
			}
		}

		// Update other fields of the Uploads entity
		existingUpload.setUploadType(uploadsDto.getUploadType());
		existingUpload.setCreatedBy(uploadsDto.getCreatedBy());

		// Save the updated entity to the repository
		Uploads updatedUpload = this.uploadsRepository.save(existingUpload);

		// Convert the updated Uploads entity to UploadsDto and return it
		return this.toDto(updatedUpload);
	}

	@Override
	public void deleteUploadById(int uploadId) {
		Uploads uploads = this.uploadsRepository.findById(uploadId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"upload file with given id is not found on server!!" + uploadId));
		this.uploadsRepository.delete(uploads);
	}

	

}
