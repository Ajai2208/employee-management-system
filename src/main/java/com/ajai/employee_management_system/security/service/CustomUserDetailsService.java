package com.ajai.employee_management_system.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        if (!"admin".equals(username)) {
            throw new UsernameNotFoundException("User not found");
        }

        return User.builder()
                .username("admin")
                .password("admin123")
                .roles("ADMIN")
                .build();
    }
}