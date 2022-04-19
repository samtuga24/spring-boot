package com.samtuga.schoolmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "assignment_id")
    private long id;
    private String assignmentTitle;
    private LocalDate deadline;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_id")
    private Grade grade;

    public Assignment(String assignmentTitle, LocalDate deadline) {
        this.assignmentTitle = assignmentTitle;
        this.deadline = deadline;
    }
}
