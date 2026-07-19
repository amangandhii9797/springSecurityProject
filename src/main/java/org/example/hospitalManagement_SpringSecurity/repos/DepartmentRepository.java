package org.example.hospitalManagement_SpringSecurity.repos;

import org.example.hospitalManagement_SpringSecurity.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}