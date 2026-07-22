package org.example.hospitalManagement_SpringSecurity.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private  final PasswordEncoder passwordEncoder;
    private final JwtAuthFilter    jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessionConfig ->
                        sessionConfig.sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                                /*removing them  just to avoid complication to test jwt security */
                        //.requestMatchers("/admin/**").hasRole("ADMIN")
                        //.requestMatchers("/doctor/**").hasAnyRole("DOCTOR", "ADMIN")
                      //  .requestMatchers("/patient/**").hasAnyRole("PATIENT", "ADMIN")

                        .requestMatchers("/public/**", "/auth/**").permitAll()
                        .anyRequest().authenticated()

                );

        // part of Jwt authentication     , we are adding our filter before UsernamePasswordAuthenticationFilter
        httpSecurity.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        // .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
