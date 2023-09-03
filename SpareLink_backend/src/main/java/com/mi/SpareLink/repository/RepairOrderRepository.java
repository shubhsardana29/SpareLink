package com.mi.SpareLink.repository;

import java.util.List;
import com.mi.SpareLink.model.RepairOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairOrderRepository extends JpaRepository<RepairOrder, Long> {
    List<RepairOrder> findByServiceCenterID(Long serviceCenterID);

    List<Long> findRepairOrderIdsByServiceCenterID(long serviceCenterID);
}
