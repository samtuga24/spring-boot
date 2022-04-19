package com.samtuga.schoolmanagement.service;

import com.samtuga.schoolmanagement.model.Admin;
import com.samtuga.schoolmanagement.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class CustomAdminDetailsService implements UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;
    private String ADMIN_NOT_FOUND_MSG = "Admin with email %s not found";
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(String.format(ADMIN_NOT_FOUND_MSG,email)));

        UserDetails user = User.withUsername(admin.getEmail())
                .password(admin.getPassword())
                .authorities("ADMIN").build();

        return user;
    }
}
