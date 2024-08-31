package com.transline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.transline.entities.Reason;

public interface ReasonRepository extends JpaRepository<Reason, Integer> {

	 @Query("SELECT r FROM Reason r WHERE r.incident.incidentId = :incidentId")
	    Reason findByIncidentId(@Param("incidentId") String incidentId);
}
