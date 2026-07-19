package org.example.hospitalManagement_SpringSecurity.controllers;


import lombok.RequiredArgsConstructor;
import org.example.hospitalManagement_SpringSecurity.dtos.AppointmentResponseDto;
import org.example.hospitalManagement_SpringSecurity.services.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final AppointmentService appointmentService;

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointmentsOfDoctor(@RequestParam Long doctorId) {
        return ResponseEntity.ok(appointmentService.getAllAppointmentsOfDoctor(doctorId));
    }

}
