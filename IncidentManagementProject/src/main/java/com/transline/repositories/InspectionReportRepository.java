package com.transline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.transline.entities.InspectionReport;

public interface InspectionReportRepository extends JpaRepository<InspectionReport, Integer> {

	@Query("SELECT w FROM InspectionReport w WHERE w.incident.id = :incidentId")
	InspectionReport findByIncidentId(@Param("incidentId") String incidentId);
}
