package com.samtuga.schoolmanagement.service;

import com.samtuga.schoolmanagement.model.Student;
import com.samtuga.schoolmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomStudentDetailsService implements UserDetailsService {
    @Autowired
    private StudentRepository studentRepository;
    private String STUDENT_NOT_FOUND = "student with email %s not found";
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException(String.format(STUDENT_NOT_FOUND,email)));

        UserDetails user = User.withUsername(student.getEmail())
                .password(student.getPassword())
                .authorities("STUDENT").build();
        return user;
    }
}
