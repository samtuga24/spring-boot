package com.samtuga.schoolmanagement.service;

import com.samtuga.schoolmanagement.model.Teacher;
import com.samtuga.schoolmanagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomTeacherDetailsService implements UserDetailsService {
    @Autowired
    private TeacherRepository teacherRepository;
    private String TEACHER_NOT_FOUND = "Teacher with email %s not found";
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Teacher teacher = teacherRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(String.format(TEACHER_NOT_FOUND,email)));
        UserDetails user = User.withUsername(teacher.getEmail())
                .password(teacher.getPassword())
                .authorities("TEACHER").build();
        return user;

    }
}
