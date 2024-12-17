package com.njit.cs631.final_project.repository;


import com.njit.cs631.final_project.entity.Appointment;
import com.njit.cs631.final_project.entity.ServiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceDetailRepository extends JpaRepository<ServiceDetail, Integer> {
    Optional<ServiceDetail> findByAppointment(Appointment appointment);
}
