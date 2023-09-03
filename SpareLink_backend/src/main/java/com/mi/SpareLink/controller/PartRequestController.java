package com.mi.SpareLink.controller;

import com.mi.SpareLink.model.RepairOrder;
import com.mi.SpareLink.model.PartRequest;
import com.mi.SpareLink.repository.RepairOrderRepository;
import com.mi.SpareLink.repository.PartRequestRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PartRequestController {

    @Autowired
    private RepairOrderRepository repairOrderRepository;

    @Autowired
    private PartRequestRepository partRequestRepository;

    @PostMapping("/getpartrequests")
    public ResponseEntity<?> getRepairOrdersWithParts(@RequestBody Map<String, Long> requestBody) {
        Long roleId = requestBody.get("roleId");
        Long serviceCenterId = requestBody.get("serviceCenterId");

        if (roleId != 1) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<RepairOrder> repairOrders = repairOrderRepository.findByServiceCenterID(serviceCenterId);

        List<Map<String, Object>> result = new ArrayList<>();

        for (RepairOrder repairOrder : repairOrders) {
            List<PartRequest> partRequests = partRequestRepository.findByRepairOrderID(repairOrder.getRepairOrderID());

            Map<String, Object> repairOrderWithParts = new HashMap<>();
            repairOrderWithParts.put("repairOrderId", repairOrder.getRepairOrderID());
            repairOrderWithParts.put("partRequests", partRequests);

            result.add(repairOrderWithParts);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/getallpartrequests")
    public ResponseEntity<List<PartRequest>> getAllPartRequests(@RequestBody Map<String, Object> requestMap) {
        int roleId = (int) requestMap.get("roleid");
        if (roleId != 2) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Fetch all part requests
        List<PartRequest> partRequests = partRequestRepository.findAll();

        return ResponseEntity.ok(partRequests);
    }

    @PostMapping("/createpartrequest")
    public ResponseEntity<PartRequest> createPartRequest(@RequestBody Map<String, Long> requestBody) {
        try {
            Long repairOrderId = requestBody.get("repairOrderId");

            PartRequest partRequest = new PartRequest();

            partRequest.setRepairOrderID(repairOrderId);

            PartRequest createdPartRequest = partRequestRepository.save(partRequest);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdPartRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
