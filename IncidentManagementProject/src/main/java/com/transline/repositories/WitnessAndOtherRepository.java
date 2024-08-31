package com.transline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.transline.entities.WitnessAndOther;

public interface WitnessAndOtherRepository extends JpaRepository<WitnessAndOther, Integer> {

	@Query("SELECT w FROM WitnessAndOther w WHERE w.incident.id = :incidentId")
	WitnessAndOther findByIncidentId(@Param("incidentId") String incidentId);

}
