package org.example.hospitalManagement_SpringSecurity.dtos;

import lombok.Data;
import org.example.hospitalManagement_SpringSecurity.models.enums.BloodGroupType;

import java.time.LocalDate;

@Data
public class PatientResponseDto {
    private Long id;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private BloodGroupType bloodGroup;
}