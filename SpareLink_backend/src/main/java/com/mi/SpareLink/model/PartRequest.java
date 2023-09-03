package com.mi.SpareLink.model;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "partrequest_temp")
public class PartRequest {

    @Id
    @Column(name = "PartRequestID")
    private Long PartRequestID;

    @Column(name = "RepairOrderID")
    private Long repairOrderID;

    @Column(name = "SparePartID")
    private Long SparePartID;

    @Column(name = "RequestedQuantity")
    private Integer RequestedQuantity;

    @Column(name = "Status")
    private String Status;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    public Long getPartRequestID() {
        return PartRequestID;
    }

    public void setPartRequestID(Long partRequestID) {
        PartRequestID = partRequestID;
    }

    public Long getRepairOrderID() {
        return repairOrderID;
    }

    public void setRepairOrderID(Long repairOrderID) {
        this.repairOrderID = repairOrderID;
    }

    public Long getSparePartID() {
        return SparePartID;
    }

    public void setSparePartID(Long sparePartID) {
        SparePartID = sparePartID;
    }

    public Integer getRequestedQuantity() {
        return RequestedQuantity;
    }

    public void setRequestedQuantity(Integer requestedQuantity) {
        RequestedQuantity = requestedQuantity;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
