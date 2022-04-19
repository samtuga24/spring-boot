package com.samtuga.schoolmanagement.repository;

import com.samtuga.schoolmanagement.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

  Optional<Grade> findByGradeName(String gradeName);

}
