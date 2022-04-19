package com.samtuga.schoolmanagement.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private long id;
    private String subjectName;
    @ManyToMany(mappedBy = "subjects", cascade = CascadeType.ALL)
    private Set<Teacher> teachers = new HashSet<>();

    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }
}
