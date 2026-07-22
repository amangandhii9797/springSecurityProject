package org.example.hospitalManagement_SpringSecurity.controllers;

import lombok.RequiredArgsConstructor;
import org.example.hospitalManagement_SpringSecurity.dtos.LoginRequestDto;
import org.example.hospitalManagement_SpringSecurity.dtos.LoginResponseDto;
import org.example.hospitalManagement_SpringSecurity.dtos.SignUpRequestDto;
import org.example.hospitalManagement_SpringSecurity.dtos.SignUpResponseDto;
import org.example.hospitalManagement_SpringSecurity.security.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signup(@RequestBody SignUpRequestDto signupRequestDto) {
        return ResponseEntity.ok(authService.signup(signupRequestDto));
    }
}