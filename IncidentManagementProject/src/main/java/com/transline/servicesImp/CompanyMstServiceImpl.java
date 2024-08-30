package com.transline.servicesImp;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.transline.dtos.CompanyMstDto;
import com.transline.dtos.UploadsDto;
import com.transline.entities.CompanyMst;
import com.transline.entities.Uploads;
import com.transline.exceptions.ResourceNotFoundException;
import com.transline.repositories.CompanyMstRepository;
import com.transline.services.CompanyMstService;
import com.transline.utils.ShortUniqueIdGenerator;

import io.jsonwebtoken.io.IOException;

@Service
public class CompanyMstServiceImpl implements CompanyMstService {

	private static final String UPLOAD_DIR = "logos/";

	@Autowired
	private CompanyMstRepository companyMstRepository;

// -------------------------------- Model Mapper-------------------------------------------------------

	@Autowired
	private ModelMapper modelMapper;

	// Method to convert Uploads entity to UploadsDto
	private CompanyMstDto toDto(CompanyMst companyMst) {
		return modelMapper.map(companyMst, CompanyMstDto.class);
	}

	// Method to convert UploadsDto to Uploads entity
	private CompanyMst toEntity(CompanyMstDto companyMstDto) {
		return modelMapper.map(companyMstDto, CompanyMst.class);
	}

	// -----------------------------------------------------------------------------------------------------

	private String getFileExtension(String filename) {
		int dotIndex = filename.lastIndexOf(".");
		return dotIndex >= 0 ? filename.substring(dotIndex) : "";
	}

//	private String generateCompanyCode(String companyName) {
//        if (companyName == null || companyName.length() < 2) {
//            throw new IllegalArgumentException("Company name must be at least 2 characters long.");
//        }
//
//        String prefix = companyName.substring(0, 2).toUpperCase();
//        Integer maxNumber = companyMstRepository.findMaxNumberForPrefix(prefix).orElse(0);
//        Integer nextNumber = maxNumber + 1;
//
//        // Format the number with leading zeros
//        String formattedNumber = String.format("%05d", nextNumber);
//
//        return prefix + formattedNumber;
//    }

	@Override
	public CompanyMstDto saveCompanyMast(CompanyMstDto companyMstDto, MultipartFile file) throws java.io.IOException {
		if (file != null && !file.isEmpty()) {
			try {
				String companyName = companyMstDto.getCmpName(); // Assuming you have this field
				if (companyName == null || companyName.isEmpty()) {
					throw new IllegalArgumentException("Company name must be provided.");
				}
				String sanitizedCompanyName = companyName.replaceAll("[^a-zA-Z0-9]", "_");
				String fileExtension = getFileExtension(file.getOriginalFilename());
				String fileName = sanitizedCompanyName + fileExtension;

				Path filePath = Paths.get(UPLOAD_DIR + fileName);
				Files.createDirectories(filePath.getParent());
				Files.write(filePath, file.getBytes());

				companyMstDto.setCmpLogo(filePath.toString());
			} catch (IOException e) {
				throw new RuntimeException("Failed to store file", e);
			}
		}

		if (companyMstDto.getCmpName() == null || companyMstDto.getCmpName().length() < 2) {
			throw new IllegalArgumentException("Company name must be at least 2 characters long.");
		}

		String prefix = companyMstDto.getCmpName().substring(0, 2).toUpperCase();
		Integer maxNumber = companyMstRepository.findMaxNumberForPrefix(prefix).orElse(0);
		Integer nextNumber = maxNumber + 1;

		// Format the number with leading zeros
		String formattedNumber = String.format("%05d", nextNumber);

		String code = prefix + formattedNumber;
		companyMstDto.setCmpCd(code);
		// String cmpCd = generateCompanyCode(companyMstDto.getCmpName());

		CompanyMst uploadsEntity = toEntity(companyMstDto);
		System.out.println("Uploads Entity before save: " + uploadsEntity.toString());
		CompanyMst savedEntity = companyMstRepository.save(uploadsEntity);
		System.out.println("Uploads Entity after save: " + savedEntity.toString());

		return toDto(savedEntity);
	}

	@Override
	public CompanyMstDto getAllCompanyMstById(String cmpCd) {
		CompanyMst companyMst = companyMstRepository.findById(cmpCd).orElseThrow(
				() -> new ResourceNotFoundException("company mst with this not found no the server" + cmpCd));
		return this.toDto(companyMst);
	}

	@Override
	public List<CompanyMstDto> getAllCompanyMst() {
		List<CompanyMst> companyMsts = this.companyMstRepository.findAll();
		List<CompanyMstDto> companyMstDtos = companyMsts.stream().map(company -> this.toDto(company))
				.collect(Collectors.toList());
		return companyMstDtos;
	}

	@Override
	public void deleteCompanyById(String cmpCd) {
		CompanyMst companyMst = this.companyMstRepository.findById(cmpCd).orElseThrow(
				() -> new ResourceNotFoundException("company mst with this not found no the server" + cmpCd));
		this.companyMstRepository.delete(companyMst);
	}

	@Override
	public CompanyMstDto updateCompanyMst(String cmpCd, CompanyMstDto companyMstDto, MultipartFile file)
			throws java.io.IOException {
		CompanyMst existingCompanyMst=this.companyMstRepository.findById(cmpCd)
					.orElseThrow(()-> new ResourceNotFoundException("Compant Mst","id",cmpCd));
		
		if (file != null && !file.isEmpty()) {
			try {
				String companyName = companyMstDto.getCmpName(); // Assuming you have this field
				if (companyName == null || companyName.isEmpty()) {
					throw new IllegalArgumentException("Company name must be provided.");
				}
				String sanitizedCompanyName = companyName.replaceAll("[^a-zA-Z0-9]", "_");
				String fileExtension = getFileExtension(file.getOriginalFilename());
				String fileName = sanitizedCompanyName + fileExtension;

				Path filePath = Paths.get(UPLOAD_DIR + fileName);
				Files.createDirectories(filePath.getParent());
				Files.write(filePath, file.getBytes());

				existingCompanyMst.setCmpLogo(filePath.toString());
			} catch (IOException e) {
				throw new RuntimeException("Failed to store file", e);
			}
		}
		if (companyMstDto.getCmpName() == null || companyMstDto.getCmpName().length() < 2) {
			throw new IllegalArgumentException("Company name must be at least 2 characters long.");
		}
		String prefix = companyMstDto.getCmpName().substring(0, 2).toUpperCase();
		Integer maxNumber = companyMstRepository.findMaxNumberForPrefix(prefix).orElse(0);
		Integer nextNumber = maxNumber + 1;
		String formattedNumber = String.format("%05d", nextNumber);

//		String code = prefix + formattedNumber;
//		existingCompanyMst.setCmpCd(code);	
		
		existingCompanyMst.setCmpName(companyMstDto.getCmpName());
		existingCompanyMst.setCmpAdd(companyMstDto.getCmpAdd());
		existingCompanyMst.setCity(companyMstDto.getCity());
		existingCompanyMst.setState(companyMstDto.getState());
		existingCompanyMst.setWebsite(companyMstDto.getWebsite());
		existingCompanyMst.setEmail(companyMstDto.getEmail());
		existingCompanyMst.setPhn1(companyMstDto.getPhn1());
		existingCompanyMst.setPhn2(companyMstDto.getPhn2());
	
		CompanyMst updatedcompanyMst=this.companyMstRepository.save(existingCompanyMst);
		
		return this.toDto(updatedcompanyMst);
	}

	
	
}
