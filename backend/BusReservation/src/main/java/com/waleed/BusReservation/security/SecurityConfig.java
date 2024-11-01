package com.waleed.BusReservation.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtEntryPoint jwtEntryPoint;
   
    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // Disable CSRF protection as we're using JWT for security
        http.csrf(csrf -> csrf.disable())
        // Configure authorization rules for HTTP requests
        .authorizeHttpRequests(auth -> auth
            // Allow all GET requests without authentication
            .requestMatchers(HttpMethod.GET).permitAll()
            // Allow all requests to auth endpoints (login, register, etc.)
            .requestMatchers("/api/auth/**").permitAll()
            // Require authentication for admin operations like adding buses, routes, and schedules
            .requestMatchers(HttpMethod.POST, "/api/bus/add", "/api/bus-route/add", "/api/bus-schedule/add")
            .authenticated()
            // Allow public access to reservation creation endpoint
            .requestMatchers(HttpMethod.POST, "/api/reservations/add").permitAll())
        // Configure exception handling for unauthorized access
        .exceptionHandling(exceptionHandlingConfigurer -> 
            exceptionHandlingConfigurer.authenticationEntryPoint(jwtEntryPoint))
        // Set session management to STATELESS since we're using JWT
        .sessionManagement(sessionManagementConfigurer -> 
            sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        
        // Add JWT authentication filter before the default authentication filter
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * Creates and configures a PasswordEncoder bean.
     * 
     * @return BCryptPasswordEncoder instance for secure password hashing
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCryptPasswordEncoder is a strong hashing function, suitable for most use cases
        return new BCryptPasswordEncoder(10);
    }

    /**
     * Creates and configures an AuthenticationManager bean.
     * 
     * @param configuration The AuthenticationConfiguration to use
     * @return AuthenticationManager instance for handling authentication requests
     * @throws Exception if there's an issue creating the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        // Retrieve the AuthenticationManager from the provided configuration
        return configuration.getAuthenticationManager();
    }
}
