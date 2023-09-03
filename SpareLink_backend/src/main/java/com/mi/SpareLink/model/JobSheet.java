// model/JobSheet.java
package com.mi.SpareLink.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "jobsheet_temp")
public class JobSheet {
    @Id
    @Column(name = "JobSheetID")
    private Long JobSheetID;

    @Column(name = "RepairOrderID")
    private Long repairOrderID;

    @Column(name = "CreatedDate")
    private Date CreatedDate;

    public Long getJobSheetID() {
        return JobSheetID;
    }

    public void setJobSheetID(Long jobSheetID) {
        JobSheetID = jobSheetID;
    }

    public Long getRepairOrderID() {
        return repairOrderID;
    }

    public void setRepairOrderID(Long repairOrderID) {
        this.repairOrderID = repairOrderID;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }
}
