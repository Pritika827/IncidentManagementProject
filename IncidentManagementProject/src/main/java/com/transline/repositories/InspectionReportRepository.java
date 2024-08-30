package com.transline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transline.entities.InspectionReport;

public interface InspectionReportRepository extends JpaRepository<InspectionReport, Integer> {

}
