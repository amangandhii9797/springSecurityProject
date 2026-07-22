package org.example.hospitalManagement_SpringSecurity.security;


import lombok.RequiredArgsConstructor;
import org.example.hospitalManagement_SpringSecurity.dtos.LoginRequestDto;
import org.example.hospitalManagement_SpringSecurity.dtos.LoginResponseDto;
import org.example.hospitalManagement_SpringSecurity.dtos.SignUpRequestDto;
import org.example.hospitalManagement_SpringSecurity.dtos.SignUpResponseDto;
import org.example.hospitalManagement_SpringSecurity.models.User;
import org.example.hospitalManagement_SpringSecurity.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // this adds a all arg cunstructor for all final fields,
// so we don't have to write it manually
// other option is using @autowired but it makes fields mutable,
// and it's harder to test since you can't just pass mocks into a constructor.
public class AuthService {


    // they all need to be final , otherwise it will fail
    private final AuthenticationManager authenticationManager;
    private  final AuthUtil authUtil;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;



    public LoginResponseDto login(LoginRequestDto loginRequestDto){
        // Authenticate manager has only method authenticate which we are calling
                  Authentication authentication = authenticationManager.authenticate(
                          new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));

           User user = (User) authentication.getPrincipal();

           // Creating auth token
           String token = authUtil.generateAccessToken(user);
           return new LoginResponseDto(token,user.getId());

    }


    // try fining the user by username, if it exists throw an exception,
    // otherwise create a new user and return the response
    public SignUpResponseDto signup(SignUpRequestDto signUpRequest){
        if (userRepository.findByUsername(signUpRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Username already taken");
        }

        User user = userRepository.save(User.builder()
                .username(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .roles(signUpRequest.getRoles())
                .build()
        );

        return new SignUpResponseDto(user.getId(), user.getUsername());

    }
}
