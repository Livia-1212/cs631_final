package com.njit.cs631.final_project.controller;


import com.njit.cs631.final_project.dto.ServiceDetailDTO;
import com.njit.cs631.final_project.service.ServiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service-details")
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
}
