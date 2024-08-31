package com.transline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.transline.entities.PoliceRemarks;

public interface PoliceRemarksRepository extends JpaRepository<PoliceRemarks, Integer> {

	@Query("SELECT w FROM PoliceRemarks w WHERE w.incident.id = :incidentId")
	PoliceRemarks findByIncidentId(@Param("incidentId") String incidentId);

}
