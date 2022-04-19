package com.samtuga.schoolmanagement.controller;

import com.samtuga.schoolmanagement.model.Student;
import com.samtuga.schoolmanagement.repository.StudentRepository;
import com.samtuga.schoolmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/admin/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        return new ResponseEntity<Student>(studentService.addStudent(student), HttpStatus.CREATED);
    }

    @GetMapping("/admin/view")
    public List<Student> viewStudents(){
       return studentService.getStudent();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Student> viewProfile(@PathVariable("id") long id){
        return new ResponseEntity<Student>(studentService.findStudent(id),HttpStatus.OK);
    }

    @DeleteMapping("/admin/remove/{id}")
    public void removeStudent(@PathVariable("id") long id){
        studentService.removeStudent(id);
    }

    @PutMapping("/admin/update/{id}")
    public void updateStudent(@PathVariable("id") long id, @RequestBody Student student){
        studentService.updateStudent(id,student);
    }
}
