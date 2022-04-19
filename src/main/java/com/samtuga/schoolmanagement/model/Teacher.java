package com.samtuga.schoolmanagement.model;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "teacher_id")
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate dob;
    private int age;
    private String location;
    private String phoneNumber;
    private String photo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "teacher_subject", joinColumns = @JoinColumn(name = "teacher_fk"),
            inverseJoinColumns = @JoinColumn(name = "subject_fk"))
    private Set <Subject> subjects = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "teacher_grade", joinColumns = @JoinColumn(name = "teacher_fk"),
            inverseJoinColumns = @JoinColumn(name = "grade_fk"))
    private Set<Grade> grades = new HashSet<>();

    public Teacher(String firstName, String lastName, String email, String password, LocalDate dob,
                   int age, String location, String phoneNumber, String photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.age = age;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.photo = photo;
    }
}
