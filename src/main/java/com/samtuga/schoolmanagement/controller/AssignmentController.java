package com.samtuga.schoolmanagement.controller;

import com.samtuga.schoolmanagement.model.Assignment;
import com.samtuga.schoolmanagement.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.chrono.Era;

@RestController
@RequestMapping("/api/assignment")
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;
    @PostMapping("/add")
    public ResponseEntity<Assignment> addAss(@RequestBody Assignment assignment){
        return new ResponseEntity<Assignment>(assignmentService.addAssignment(assignment), HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Assignment> viewAss(@PathVariable("id") long id){
        return new ResponseEntity<Assignment>(assignmentService.viewAssignment(id),HttpStatus.FOUND);
    }


}
