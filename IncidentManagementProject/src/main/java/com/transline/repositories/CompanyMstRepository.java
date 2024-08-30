package com.transline.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.transline.entities.CompanyMst;

public interface CompanyMstRepository extends JpaRepository<CompanyMst, String>{

	@Query("SELECT COALESCE(MAX(CAST(SUBSTRING(cmpCd, 3) AS integer)), 0) FROM cmp_mst WHERE cmpCd LIKE %?1%")
    Optional<Integer> findMaxNumberForPrefix(String prefix);	
	
//	@Query("SELECT COALESCE(MAX(CAST(SUBSTRING(cmpCd, 3) AS integer)), 0) FROM cmp_mst WHERE cmpCd LIKE CONCAT(?1, '%')")
//    Optional<Integer> findMaxNumberForPrefix(String prefix);
	
	CompanyMst findByCmpCd(String cmpCd); // Ensure this method is defined
}
