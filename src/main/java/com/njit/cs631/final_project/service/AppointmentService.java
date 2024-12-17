package com.njit.cs631.final_project.service;

import com.njit.cs631.final_project.dto.AppointmentDTO;
import com.njit.cs631.final_project.dto.AppointmentDetailDTO;
import com.njit.cs631.final_project.dto.ServiceDetailDTO;
import com.njit.cs631.final_project.entity.*;
import com.njit.cs631.final_project.repository.AppointmentRepository;
import com.njit.cs631.final_project.repository.CustomerRepository;
import com.njit.cs631.final_project.repository.ServicePackageRepository;
import com.njit.cs631.final_project.repository.ServiceDetailRepository;
import com.njit.cs631.final_project.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ServiceDetailRepository serviceDetailRepository;

    @Autowired
    private ServicePackageRepository servicePackageRepository;

    @Autowired
    ServiceDetailService serviceDetailService;

    public List<AppointmentDTO> getUpcomingAppointments() {
        return appointmentRepository.findUpcomingAppointments(LocalDateTime.now());
    }

    /**
     * Creates a new appointment and saves it to the repository.
     * 
     * @param firstName        First name of the customer.
     * @param lastName         Last name of the customer.
     * @param scheduledTime    Scheduled time for the appointment.
     * @param servicePackageId ID of the service package.
     * @param time             Estimated time for the service (as LocalTime).
     * @param vin              VIN of the vehicle.
     */
    public void createAppointment(
            String firstName,
            String lastName,
            LocalDateTime scheduledTime,
            Long servicePackageId,
            LocalTime time,
            String vin) {

        // Fetch customer by name
        Customer customer = customerRepository.findByFirstNameAndLastName(firstName, lastName)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Fetch vehicle by VIN
        Vehicle vehicle = vehicleRepository.findByVin(vin);
        if (vehicle == null) {
            throw new RuntimeException("Vehicle not found");
        }

        // Fetch service package
        ServicePackage servicePackage = servicePackageRepository.findById(servicePackageId)
                .orElseThrow(() -> new RuntimeException("Service package not found"));

        // Create and save appointment
        Appointment appointment = new Appointment();
        appointment.setScheduledTime(scheduledTime);
        appointment.setTime(time); // Setting the time as LocalTime
        appointment.setCustomer(customer);
        appointment.setVehicle(vehicle);
        appointment.setServicePackage(servicePackage);

        appointmentRepository.save(appointment);

        // Create and Save ServiceDetail
        ServiceDetail serviceDetail = new ServiceDetail();
        serviceDetail.setAppointment(appointment);
        serviceDetail.setArrivalTime(scheduledTime); // Default arrival time = scheduled time
        serviceDetail.setPickUpTime(null); // Initially null
        serviceDetail.setServicePerformed(""); // No service performed yet
        // serviceDetail.setPartsUsed(""); // No parts used yet
        serviceDetail.setLaborHours(BigDecimal.ZERO); // Default to 0
        serviceDetail.setTotalCost(BigDecimal.ZERO); // Default to 0

        serviceDetailRepository.save(serviceDetail);
    }

    public AppointmentDetailDTO getAppointmentDetailById(Long appointmentId) {
        // Fetch appointment by ID
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + appointmentId));

        // Fetch service detail related to the appointment
        ServiceDetail serviceDetail = serviceDetailRepository.findByAppointment(appointment)
                .orElse(null);

        // Map data to DTO
        return mapToAppointmentDetailDTO(appointment, serviceDetail);
    }

    private AppointmentDetailDTO mapToAppointmentDetailDTO(Appointment appointment, ServiceDetail serviceDetail) {
        AppointmentDetailDTO dto = new AppointmentDetailDTO();
        dto.setAppointmentId(appointment.getAppointmentId());
        dto.setScheduledTime(appointment.getScheduledTime());
        dto.setEstimatedTime(appointment.getEstimatedTime());
        dto.setCustomerName(appointment.getCustomer().getFirstName() + " " + appointment.getCustomer().getLastName());
        dto.setVin(appointment.getVehicle().getVin());
        dto.setPackageName(appointment.getServicePackage().getPackageName());
        dto.setAppointmentStatus(appointment.getAppointmentStatus());

        if (serviceDetail != null) {
            dto.setArrivalTime(serviceDetail.getArrivalTime());
            dto.setPickUpTime(serviceDetail.getPickUpTime());
            dto.setServicePerformed(serviceDetail.getServicePerformed());
            dto.setLaborHours(serviceDetail.getLaborHours());
            dto.setTotalCost(serviceDetail.getTotalCost());
        }

        return dto;
    }
}