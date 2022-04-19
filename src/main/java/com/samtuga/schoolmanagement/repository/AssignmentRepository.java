package com.samtuga.schoolmanagement.repository;

import com.samtuga.schoolmanagement.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    Optional<Assignment> findByAssignmentTitle(String assignmentTitle);
    Optional<Assignment>findById(long id);
}
