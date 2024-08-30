package com.transline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transline.entities.Staffs;

@Repository
public interface StaffRepository extends JpaRepository<Staffs, Integer> {

}
