package com.transline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.transline.entities.CompanyMst;
import com.transline.entities.Office;

public interface OfficeRepository extends JpaRepository<Office, String>{
	@Query("SELECT MAX(o.offCd) FROM Office o")
    String findMaxOffCd();
	
	Office findByOffCd(String offCd);
}
