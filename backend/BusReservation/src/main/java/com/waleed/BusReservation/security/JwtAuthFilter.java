package com.waleed.BusReservation.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.waleed.BusReservation.service.CustomUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        // Extract the JWT token from the request
        String token = getTokenFromRequest(request);

        // Check if the token exists and is valid
        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
            // Extract the username from the token
            String username = jwtTokenProvider.getUsernameFromJwt(token);

            // Load the user details associated with the username
            UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

            // Create an authentication token with the user details and authorities
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities());

            // Set additional details for the authentication token
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Set the authentication in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }

    /**
     * Extracts the JWT token from the request's Authorization header.
     *
     * @param request The HttpServletRequest object
     * @return The JWT token if present, null otherwise
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        // Get the Authorization header from the request
        String bearerToken = request.getHeader("Authorization");

        // Check if the header exists and starts with "Bearer "
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            // Extract the token by removing the "Bearer " prefix
            return bearerToken.substring(7);
        }
        // Return null if no valid token is found
        return null;
    }
}
