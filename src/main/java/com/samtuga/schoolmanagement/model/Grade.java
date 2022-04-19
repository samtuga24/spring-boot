package com.samtuga.schoolmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private long id;

    private String gradeName;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL)
    private Set<Student> students = new HashSet<>();

    @ManyToMany(mappedBy = "grades")
    private Set<Teacher>teachers= new HashSet<>();

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Assignment> assignments = new HashSet<>();

    public Grade(String gradeName) {
        this.gradeName = gradeName;

    }
}
