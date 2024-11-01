package com.waleed.BusReservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.waleed.BusReservation.entity.AppUser;
import com.waleed.BusReservation.repository.AppUserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Attempt to find the user in the database by their username
        AppUser appUser = appUserRepository.findByUserName(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Convert the AppUser entity to a Spring Security UserDetails object
        return User.builder()
            .username(appUser.getUserName())  // Set the username
            .password(appUser.getPassword())  // Set the password (should be encoded)
            .roles(appUser.getRole())         // Set the user's role(s)
            .build();                         // Build and return the UserDetails object
    }
}
