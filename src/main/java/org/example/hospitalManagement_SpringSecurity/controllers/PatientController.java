package org.example.hospitalManagement_SpringSecurity.controllers;

import lombok.RequiredArgsConstructor;
import org.example.hospitalManagement_SpringSecurity.dtos.AppointmentResponseDto;
import org.example.hospitalManagement_SpringSecurity.dtos.CreateAppointmentRequestDto;
import org.example.hospitalManagement_SpringSecurity.dtos.PatientResponseDto;
import org.example.hospitalManagement_SpringSecurity.services.AppointmentService;
import org.example.hospitalManagement_SpringSecurity.services.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final AppointmentService appointmentService;

    @PostMapping("/appointments")
    public ResponseEntity<AppointmentResponseDto> createNewAppointment(@RequestBody CreateAppointmentRequestDto createAppointmentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createNewAppointment(createAppointmentRequestDto));
    }

    @GetMapping("/profile")
    private ResponseEntity<PatientResponseDto> getPatientProfile() {
        Long patientId = 4L;
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }

}