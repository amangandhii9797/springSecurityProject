package org.example.hospitalManagement_SpringSecurity.repos;

import org.example.hospitalManagement_SpringSecurity.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}