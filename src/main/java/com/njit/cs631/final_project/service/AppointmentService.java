package com.njit.cs631.final_project.service;

import com.njit.cs631.final_project.dto.AppointmentDTO;
import com.njit.cs631.final_project.entity.Appointment;
import com.njit.cs631.final_project.entity.Customer;
import com.njit.cs631.final_project.entity.ServicePackage;
import com.njit.cs631.final_project.entity.Vehicle;
import com.njit.cs631.final_project.repository.AppointmentRepository;
import com.njit.cs631.final_project.repository.CustomerRepository;
import com.njit.cs631.final_project.repository.ServicePackageRepository;
import com.njit.cs631.final_project.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ServicePackageRepository servicePackageRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    /**
     * Retrieves a list of upcoming appointments.
     * 
     * @return List of upcoming appointments as AppointmentDTO objects.
     */
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
    }
}
