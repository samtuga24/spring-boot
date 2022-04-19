package com.samtuga.schoolmanagement.service;

import com.samtuga.schoolmanagement.model.Grade;
import com.samtuga.schoolmanagement.model.Student;
import com.samtuga.schoolmanagement.repository.GradeRepository;
import com.samtuga.schoolmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class StudentService {
    private final String USER_EXISTS_ERROR = "user with email %s already exists";
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Student addStudent(Student student) {
        studentRepository.findByEmail(student.getEmail()).ifPresent(param -> {
            throw new IllegalStateException(String.format(USER_EXISTS_ERROR, student.getEmail()));
        });

        Grade grade = gradeRepository.findByGradeName(student.getGrade().getGradeName()).orElse(null);
        if(null == grade) {
            grade = new Grade();
        }
        grade.setGradeName(student.getGrade().getGradeName());
        student.setGrade(grade);
        student.setAge(Period.between(student.getDob(),LocalDate.now()).getYears());
        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    public void removeStudent(long id){
        studentRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(""));
        studentRepository.deleteById(id);

    }

    public void updateStudent(long id, Student student){
        studentRepository.findById(id)
                .orElseThrow(()->new IllegalStateException(""));
        student.setId(id);
        studentRepository.save(student);

    }

    public List<Student> getStudent(){
       return studentRepository.findAll();
    }

    public Student findStudent(long id){
        return studentRepository.findById(id)
                .orElseThrow(()->new IllegalStateException("Student not found"));
    }

    }

