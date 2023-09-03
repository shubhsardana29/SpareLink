package com.mi.SpareLink.repository;

import com.mi.SpareLink.model.JobSheet;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobSheetRepository extends JpaRepository<JobSheet, Long> {

    List<JobSheet> findByRepairOrderID(Long repairOrderId);
}
