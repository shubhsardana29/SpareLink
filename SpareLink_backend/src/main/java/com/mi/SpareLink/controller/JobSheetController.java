// controller/JobSheetController.java
package com.mi.SpareLink.controller;

import com.mi.SpareLink.model.JobSheet;
import com.mi.SpareLink.model.RepairOrder;
import com.mi.SpareLink.repository.JobSheetRepository;
import com.mi.SpareLink.repository.RepairOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

@RestController
public class JobSheetController {

    @Autowired
    private JobSheetRepository jobSheetRepository;

    @Autowired
    private RepairOrderRepository repairOrderRepository;

    @PostMapping("/getjobsheets")
    public ResponseEntity<?> getJobSheetsWithRepairOrders(@RequestBody Map<String, Long> requestBody) {
        Long roleId = requestBody.get("roleId");
        Long serviceCenterId = requestBody.get("serviceCenterId");

        if (roleId != 1) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<RepairOrder> repairOrders = repairOrderRepository.findByServiceCenterID(serviceCenterId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (RepairOrder repairOrder : repairOrders) {
            List<JobSheet> jobSheets = jobSheetRepository.findByRepairOrderID(repairOrder.getRepairOrderID());

            Map<String, Object> repairOrderWithJobSheets = new HashMap<>();
            repairOrderWithJobSheets.put("repairOrderId", repairOrder.getRepairOrderID());
            repairOrderWithJobSheets.put("jobSheets", jobSheets);

            result.add(repairOrderWithJobSheets);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/getalljobsheets")
    public ResponseEntity<List<JobSheet>> getAllJobSheets(@RequestBody Map<String, Object> requestMap) {
        int roleId = (int) requestMap.get("roleId");
        if (roleId != 1) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Fetch all job sheets
        List<JobSheet> jobSheets = jobSheetRepository.findAll();

        return ResponseEntity.ok(jobSheets);
    }

    @PostMapping("/createjobsheet")
    public ResponseEntity<?> createJobSheet(@RequestBody Long serviceCenterId) {
        // Create a new RepairOrder entry
        RepairOrder repairOrder = new RepairOrder();
        repairOrder.setServiceCenterID(serviceCenterId);
        repairOrder = repairOrderRepository.save(repairOrder);

        // Create a new JobSheet entry
        JobSheet jobSheet = new JobSheet();
        jobSheet.setRepairOrderID(repairOrder.getRepairOrderID());
        jobSheet.setCreatedDate(new Date());
        jobSheet = jobSheetRepository.save(jobSheet);

        return ResponseEntity.ok(jobSheet);
    }
}
