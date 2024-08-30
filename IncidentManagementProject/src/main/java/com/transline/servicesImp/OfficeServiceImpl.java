package com.transline.servicesImp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transline.dtos.IncidentsDto;
import com.transline.dtos.OfficeDto;
import com.transline.entities.CompanyMst;
import com.transline.entities.Incidents;
import com.transline.entities.Office;
import com.transline.exceptions.ResourceNotFoundException;
import com.transline.repositories.CompanyMstRepository;
import com.transline.repositories.OfficeRepository;
import com.transline.services.OfficeService;

@Service
public class OfficeServiceImpl implements OfficeService {

	@Autowired
	private OfficeRepository officeRepository;

	@Autowired
	private CompanyMstRepository companyMstRepository;

// -----------------Model Mapper--------------------------------------------------------------
	@Autowired
	private ModelMapper modelMapper;

	public Office dtoToOffice(OfficeDto officeDto) {
		Office offices = new Office();
		offices.setOffCd(officeDto.getOffCd());

		// Fetch CompanyMst entity using cmpCd
		CompanyMst companyMst = companyMstRepository.findByCmpCd(officeDto.getCmpCd());

		if (companyMst != null) {
			offices.setCompanyMst(companyMst);
		} else {
			throw new ResourceNotFoundException("CompanyMst", "cmpCd", officeDto.getCmpCd());
		}
		offices.setOffName(officeDto.getOffName());
		offices.setOffType(officeDto.getOffType());
		offices.setOffAddress(officeDto.getOffAddress());
		offices.setCtlCd(officeDto.getCtlCd());
		offices.setEmail(officeDto.getEmail());
		offices.setPhoneNo(officeDto.getPhoneNo());
		offices.setContactPerson(officeDto.getContactPerson());
		offices.setDisable(officeDto.getDisable());
		offices.setWorkingStatus(officeDto.getWorkingStatus());

		return offices;
	}

	public OfficeDto officeToDto(Office office) {
		OfficeDto officeDto = new OfficeDto();
		officeDto.setOffCd(office.getOffCd());

		if (office.getCompanyMst() != null) {
			officeDto.setCmpCd(office.getCompanyMst().getCmpCd());
		}

		officeDto.setOffName(office.getOffName());
		officeDto.setOffType(office.getOffType());
		officeDto.setOffAddress(office.getOffAddress());
		officeDto.setCtlCd(office.getCtlCd());
		officeDto.setEmail(office.getEmail());
		officeDto.setPhoneNo(office.getPhoneNo());
		officeDto.setContactPerson(office.getContactPerson());
		officeDto.setDisable(office.getDisable());
		officeDto.setWorkingStatus(office.getWorkingStatus());

		return officeDto;
	}

// ---------------------------------------------------------------------------------------------

	public String generateNextOffCd() {
		String maxOffCd = officeRepository.findMaxOffCd();

		if (maxOffCd == null) {
			return "office00001";
		}

		String numericPart = maxOffCd.replaceAll("\\D", ""); // Extract numeric part
		int nextNumber = Integer.parseInt(numericPart) + 1;

		return String.format("office%05d", nextNumber);
	}

	@Override
	public OfficeDto saveOffice(OfficeDto officeDto) {
		Office office = this.dtoToOffice(officeDto);

		CompanyMst companyMst = companyMstRepository.findByCmpCd(officeDto.getCmpCd());
		if (companyMst != null) {
			office.setCompanyMst(companyMst);
		} else {
			throw new ResourceNotFoundException("CompanyMst", "cmpCd", officeDto.getCmpCd());
		}
		// Generate a new offCd
		String newOffCd = generateNextOffCd();
		office.setOffCd(newOffCd);

		Office savedOffice = officeRepository.save(office);
		return this.officeToDto(savedOffice);
	}

	@Override
	public List<OfficeDto> getAllOfficeDetails() {
		List<Office> offices = this.officeRepository.findAll();
		List<OfficeDto> officeDtos = offices.stream().map(office -> this.officeToDto(office))
				.collect(Collectors.toList());
		return officeDtos;
	}

	@Override
	public OfficeDto getOfficeById(String offCd) {
		Office office = this.officeRepository.findById(offCd)
				.orElseThrow(() -> new ResourceNotFoundException("Office", "id", offCd));
		return this.officeToDto(office);
	}

	@Override
	public Office updateOfficeDto(OfficeDto officeDto, String offCd) {
		Office office=this.officeRepository.findById(offCd)
				.orElseThrow(()-> new ResourceNotFoundException("office","id",offCd));
		office.setOffName(officeDto.getOffName());
		office.setOffType(officeDto.getOffType());
		office.setOffAddress(officeDto.getOffAddress());
		office.setCtlCd(officeDto.getCtlCd());
		office.setEmail(officeDto.getEmail());
		office.setPhoneNo(officeDto.getPhoneNo());
		office.setContactPerson(officeDto.getContactPerson());
		office.setDisable(officeDto.getDisable());
		office.setWorkingStatus(officeDto.getWorkingStatus());
	
		return office;
	}
	
	


	@Override
	public void deleteOffice(String offCd) {
		Office office=this.officeRepository.findById(offCd)
					.orElseThrow(()-> new ResourceNotFoundException());
		this.officeRepository.delete(office);
	}

}
