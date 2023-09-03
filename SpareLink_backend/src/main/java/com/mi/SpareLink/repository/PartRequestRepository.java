package com.mi.SpareLink.repository;

import java.util.List;
import com.mi.SpareLink.model.PartRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRequestRepository extends JpaRepository<PartRequest, Long> {
    List<PartRequest> findByRepairOrderID(Long RepairOrderID);
}
