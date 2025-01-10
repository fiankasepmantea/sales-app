package com.salesapp.sales_app.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve user from the database or other sources here
        if ("admin".equals(username)) {
            return User.builder()
                    .username("admin")
                    .password(passwordEncoder().encode("adminpassword"))
                    .roles("ADMIN")
                    .build();
        } else if ("kasir".equals(username)) {
            return User.builder()
                    .username("kasir")
                    .password(passwordEncoder().encode("kasirpassword"))
                    .roles("KASIR")
                    .build();
        }
        throw new UsernameNotFoundException("User not found");
    }
}

