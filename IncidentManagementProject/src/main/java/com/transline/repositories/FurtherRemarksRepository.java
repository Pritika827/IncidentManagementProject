package com.transline.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transline.entities.FurtherRemarks;

@Repository
public interface FurtherRemarksRepository extends JpaRepository<FurtherRemarks, Integer> {
	
	List<FurtherRemarks> findByIncidentIncidentId(String incidentId);
}
