package com.transline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transline.entities.Reason;

public interface ReasonRepository extends JpaRepository<Reason, Integer> {

}
