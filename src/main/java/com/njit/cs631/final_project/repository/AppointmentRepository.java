package com.njit.cs631.final_project.repository;
import com.njit.cs631.final_project.dto.AppointmentDTO;
import com.njit.cs631.final_project.entity.Appointment;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT new com.njit.cs631.final_project.dto.AppointmentDTO( " +
            "sa.appointmentId, sa.scheduledTime, sa.estimatedTime, sa.appointmentStatus, " +
            "v.vin, c.firstName, c.lastName, sp.packageName) " +
            "FROM Appointment sa " +
            "JOIN sa.vehicle v " +
            "JOIN sa.customer c " +
            "JOIN sa.servicePackage sp " )
    List<AppointmentDTO> findUpcomingAppointments(LocalDateTime currentDate);

}
