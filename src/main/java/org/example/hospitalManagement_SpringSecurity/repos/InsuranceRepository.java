package org.example.hospitalManagement_SpringSecurity.repos;

import org.example.hospitalManagement_SpringSecurity.models.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}