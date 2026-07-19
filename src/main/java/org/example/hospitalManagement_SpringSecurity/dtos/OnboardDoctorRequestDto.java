package org.example.hospitalManagement_SpringSecurity.dtos;


import lombok.Data;

@Data
public class OnboardDoctorRequestDto {
    private Long userId;
    private String specialization;
    private String name;
}
