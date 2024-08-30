package com.transline.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.transline.dtos.CompanyMstDto;

public interface CompanyMstService {

	CompanyMstDto saveCompanyMast(CompanyMstDto companyMstDto, MultipartFile file) throws IOException;

	CompanyMstDto getAllCompanyMstById(String cmpCd);

	List<CompanyMstDto> getAllCompanyMst();

	void deleteCompanyById(String cmpCd);

	CompanyMstDto updateCompanyMst(String cmpCd, CompanyMstDto companyMstDto, MultipartFile logo) throws IOException;

}
