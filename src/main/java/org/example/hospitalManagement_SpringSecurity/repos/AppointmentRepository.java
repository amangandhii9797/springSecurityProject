package org.example.hospitalManagement_SpringSecurity.repos;



import org.example.hospitalManagement_SpringSecurity.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}