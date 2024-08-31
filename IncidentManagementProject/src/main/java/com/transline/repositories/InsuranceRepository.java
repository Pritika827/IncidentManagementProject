package com.transline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.transline.entities.Insurance;

public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {

	@Query("SELECT w FROM Insurance w WHERE w.incident.id = :incidentId")
	Insurance findByIncidentId(@Param("incidentId") String incidentId);
}
