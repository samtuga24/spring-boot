package com.samtuga.schoolmanagement.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDate;

@Entity
@Table(name = "admin")
@Data
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id", nullable = false)
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

    public Admin(String firstName, String lastName, String email, String password,
                 int age, LocalDate dob, String location, String phoneNumber, String photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.dob = dob;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.photo = photo;
    }
}
