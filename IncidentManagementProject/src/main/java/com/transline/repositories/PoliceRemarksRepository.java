package com.transline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transline.entities.PoliceRemarks;

public interface PoliceRemarksRepository extends JpaRepository<PoliceRemarks, Integer> {
	
}
