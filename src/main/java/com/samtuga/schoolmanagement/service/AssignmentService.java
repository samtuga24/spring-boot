package com.samtuga.schoolmanagement.service;

import com.samtuga.schoolmanagement.model.Assignment;
import com.samtuga.schoolmanagement.model.Grade;
import com.samtuga.schoolmanagement.repository.AssignmentRepository;
import com.samtuga.schoolmanagement.repository.GradeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private GradeRepository gradeRepository;

    public Assignment addAssignment(Assignment assignment){
        Grade grade = gradeRepository.findByGradeName(assignment.getGrade().getGradeName()).orElse(null);
        if(null ==  grade){
            grade = new Grade();
        }
        grade.setGradeName(assignment.getGrade().getGradeName());
        assignment.setGrade(grade);
        log.info(grade.toString());
        return assignmentRepository.save(assignment);
    }

    public Assignment viewAssignment(long id){
        return assignmentRepository.findById(id)
                .orElseThrow(()-> new UsernameNotFoundException("No assignment found"));
    }
}
