package com.transline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.transline.entities.FurtherRemarks;
import com.transline.entities.InspectionReport;

@Repository
public interface FurtherRemarksRepository extends JpaRepository<FurtherRemarks, Integer> {

	// List<FurtherRemarks> findByIncidentIncidentId(String incidentId);

	@Query("SELECT w FROM FurtherRemarks w WHERE w.incident.id = :incidentId")
	FurtherRemarks findByIncidentId(@Param("incidentId") String incidentId);

}
