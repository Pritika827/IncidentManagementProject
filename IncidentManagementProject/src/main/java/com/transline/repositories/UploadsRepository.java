package com.transline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transline.entities.Uploads;

public interface UploadsRepository extends JpaRepository<Uploads, Integer> {

}
