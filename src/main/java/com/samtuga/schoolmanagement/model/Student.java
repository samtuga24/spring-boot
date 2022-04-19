package com.samtuga.schoolmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
@Entity
@NoArgsConstructor
@Data

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id",nullable = false)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int age;
    private LocalDate dob;
    private String location;
    private String phoneNumber;
    private String photo;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_fk")
    private Grade grade;
    public Student(String firstName, String lastName, String email, String password,
                   LocalDate dob, String location, String phoneNumber, String photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.photo = photo;
    }
}
