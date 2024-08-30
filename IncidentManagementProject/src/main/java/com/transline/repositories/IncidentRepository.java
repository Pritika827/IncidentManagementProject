package com.transline.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.transline.entities.Incidents;

@Repository
public interface IncidentRepository extends JpaRepository<Incidents, String> {

	@Query("SELECT MAX(i.incidentId) FROM Incidents i WHERE i.incidentId LIKE :idPrefix%")
	String findMaxIncidentId(@Param("idPrefix") String idPrefix);
	
	@Query("SELECT i.incidentId FROM Incidents i ORDER BY i.incidentId")
	List<String> getAllIncidentIds();
	
	// boolean existsByIncidentId(String incidentId);
}
