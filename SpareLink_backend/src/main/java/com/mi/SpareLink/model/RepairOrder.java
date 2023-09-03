package com.mi.SpareLink.model;

import jakarta.persistence.*;

@Entity
@Table(name = "repairorder_temp")
public class RepairOrder {

    @Id
    @Column(name = "RepairOrderID")
    private Long repairOrderID;

    @Column(name = "ServiceCenterID")
    private Long serviceCenterID;

    public Long getRepairOrderID() {
        return repairOrderID;
    }

    public void setRepairOrderID(Long repairOrderID) {
        this.repairOrderID = repairOrderID;
    }

    public Long getServiceCenterID() {
        return serviceCenterID;
    }

    public void setServiceCenterID(Long serviceCenterID) {
        this.serviceCenterID = serviceCenterID;
    }

}