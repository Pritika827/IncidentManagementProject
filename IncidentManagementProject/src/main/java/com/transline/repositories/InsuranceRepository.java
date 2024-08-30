package com.transline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transline.entities.Insurance;

public interface InsuranceRepository extends JpaRepository<Insurance, Integer>{

}
