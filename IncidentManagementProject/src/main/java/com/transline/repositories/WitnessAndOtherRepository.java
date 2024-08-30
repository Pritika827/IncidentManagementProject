package com.transline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transline.dtos.WitnessAndOtherDto;
import com.transline.entities.WitnessAndOther;

public interface WitnessAndOtherRepository extends JpaRepository<WitnessAndOther, Integer> {

}
