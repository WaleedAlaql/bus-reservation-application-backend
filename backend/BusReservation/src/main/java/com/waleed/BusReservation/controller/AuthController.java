package com.waleed.BusReservation.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import com.waleed.BusReservation.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.waleed.BusReservation.entity.AppUser;
import com.waleed.BusReservation.entity.AuthResponseModel;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.BadCredentialsException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Value("${app.jwt.expiration.milliseconds}")
    private Long jwtExpiration;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseModel> login(@RequestBody AppUser user) {
        System.out.println("Attempting login for user: " + user.getUserName());
        try {
            // Authenticate the user with provided credentials
            final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
            );
            // Set the authenticated user in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate a JWT token for the authenticated user
            final String jwtToken = jwtTokenProvider.generateToken(authentication);

            // Create the response model with authentication details
            AuthResponseModel authResponseModel = new AuthResponseModel(
                HttpStatus.OK.toString(),
                "User logged in successfully",
                jwtToken,
                System.currentTimeMillis(),
                jwtExpiration
            );

            return ResponseEntity.ok(authResponseModel);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new AuthResponseModel(
                    HttpStatus.UNAUTHORIZED.toString(),
                    "Invalid username or password",
                    null,
                    System.currentTimeMillis(),
                    null
                ));
        }
    }
}
