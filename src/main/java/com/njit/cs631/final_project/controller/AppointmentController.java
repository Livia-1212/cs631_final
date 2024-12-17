package com.njit.cs631.final_project.controller;

import com.njit.cs631.final_project.dto.AppointmentDTO;
import com.njit.cs631.final_project.dto.AppointmentDetailDTO;
import com.njit.cs631.final_project.dto.AppointmentRequestDTO;
import com.njit.cs631.final_project.dto.ServiceDetailDTO;
import com.njit.cs631.final_project.entity.Appointment;
import com.njit.cs631.final_project.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


    // Get all upcoming appointments
    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getUpcomingAppointments() {
        return ResponseEntity.ok(appointmentService.getUpcomingAppointments());
    }

//    // Filter appointments by VIN
//    @GetMapping("/filter")
//    public ResponseEntity<List<Appointment>> filterAppointments(@RequestParam String vin) {
//        return ResponseEntity.ok(appointmentService.filterAppointmentsByVin(vin));
//    }

    // Create a new appointment
    @PostMapping
    public ResponseEntity<String> createAppointment(@RequestBody AppointmentRequestDTO appointmentRequestDTO) {

        try {
            appointmentService.createAppointment(
                    appointmentRequestDTO.getFirstName(),
                    appointmentRequestDTO.getLastName(),
                    appointmentRequestDTO.getScheduledTime(),
                    appointmentRequestDTO.getServicePackage(),
                    appointmentRequestDTO.getEstimatedTime(),
                    appointmentRequestDTO.getVin()
            );
            return new ResponseEntity<>("Appointment created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDetailDTO> getAppointmentDetailById(@PathVariable Long id) {
        AppointmentDetailDTO appointmentDetail = appointmentService.getAppointmentDetailById(id);
        return ResponseEntity.ok(appointmentDetail);
    }


}
