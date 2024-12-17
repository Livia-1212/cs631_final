package com.njit.cs631.final_project.controller;


import com.njit.cs631.final_project.dto.AppointmentDTO;
import com.njit.cs631.final_project.dto.ServiceDetailDTO;
import com.njit.cs631.final_project.entity.Appointment;
import com.njit.cs631.final_project.entity.ServiceDetail;
import com.njit.cs631.final_project.service.ServiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/service-details")
public class ServiceDetailController {

    @Autowired
    private ServiceDetailService serviceDetailService;

    @PostMapping
    public ResponseEntity<String> addServiceDetail(@RequestBody ServiceDetailDTO serviceDetailDTO) {
        try {
            serviceDetailService.addServiceDetail(serviceDetailDTO);
            return new ResponseEntity<>("Service details added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateServiceDetail(@RequestBody ServiceDetailDTO serviceDetailDTO) {
        try {
            serviceDetailService.updateServiceDetail(serviceDetailDTO);
            return new ResponseEntity<>("Service detail updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
