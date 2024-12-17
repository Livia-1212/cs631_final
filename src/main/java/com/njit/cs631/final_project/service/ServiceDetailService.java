package com.njit.cs631.final_project.service;


import com.njit.cs631.final_project.dto.ServiceDetailDTO;
import com.njit.cs631.final_project.entity.Appointment;
import com.njit.cs631.final_project.entity.ServiceDetail;
import com.njit.cs631.final_project.repository.AppointmentRepository;
import com.njit.cs631.final_project.repository.ServiceDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceDetailService {

    private final AppointmentRepository appointmentRepository;
    private final ServiceDetailRepository serviceDetailRepository;

    @Autowired
    public ServiceDetailService(AppointmentRepository appointmentRepository, ServiceDetailRepository serviceDetailRepository) {
        this.appointmentRepository = appointmentRepository;
        this.serviceDetailRepository = serviceDetailRepository;
    }

    public ServiceDetail addServiceDetail(ServiceDetailDTO serviceDetailDTO) {
        Appointment appointment = appointmentRepository.findById(serviceDetailDTO.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        ServiceDetail serviceDetail = new ServiceDetail();
        serviceDetail.setAppointment(appointment);
        serviceDetail.setArrivalTime(serviceDetailDTO.getArrivalTime());
        serviceDetail.setPickUpTime(serviceDetailDTO.getPickUpTime());
        serviceDetail.setServicePerformed(serviceDetailDTO.getServicePerformed());
        serviceDetail.setLaborHours(serviceDetailDTO.getLaborHours());
        serviceDetail.setTotalCost(serviceDetailDTO.getTotalCost());

        return serviceDetailRepository.save(serviceDetail);
    }
    public void updateServiceDetail(ServiceDetailDTO serviceDetailDTO) {
        Appointment appointment = appointmentRepository.findById(serviceDetailDTO.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        ServiceDetail serviceDetail = serviceDetailRepository.findByAppointment(appointment)
                .orElse(new ServiceDetail());

        serviceDetail.setAppointment(appointment);
        serviceDetail.setArrivalTime(serviceDetailDTO.getArrivalTime());
        serviceDetail.setPickUpTime(serviceDetailDTO.getPickUpTime());
        serviceDetail.setServicePerformed(serviceDetailDTO.getServicePerformed());
//        serviceDetail.setPartsUsed(serviceDetailDTO.getPartsUsed());
        serviceDetail.setLaborHours(serviceDetailDTO.getLaborHours());
        serviceDetail.setTotalCost(serviceDetailDTO.getTotalCost());

        serviceDetailRepository.save(serviceDetail);
    }

}

