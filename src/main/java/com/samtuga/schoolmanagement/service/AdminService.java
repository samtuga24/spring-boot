package com.samtuga.schoolmanagement.service;

import com.samtuga.schoolmanagement.model.Admin;
import com.samtuga.schoolmanagement.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final String ADMIN_NOT_FOUND_MSG = "admin with name %s not found";

    public Admin saveAdmin( Admin admin){
        admin.setAge(Period.between(admin.getDob(),LocalDate.now()).getYears());
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    public List<Admin > ViewAdmin(){
        return adminRepository.findAll();
    }

//    public Admin searchAdmin(String lastName){
//        return adminRepository.findAllAdminByLastName(lastName)
//                .orElseThrow(() -> new IllegalStateException("Admin not found"));
//    }

    public void removeAdmin(long id){
        adminRepository.findById(id)
                .orElseThrow(()->new IllegalStateException(""));
        adminRepository.deleteById(id);
    }

    public void updateAdmin(long id, Admin admin){
        adminRepository.findById(id)
                .orElseThrow(()->new IllegalStateException("Admin not found"));
        admin.setId(id);
        adminRepository.save(admin);

    }

}
