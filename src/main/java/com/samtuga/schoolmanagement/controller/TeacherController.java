package com.samtuga.schoolmanagement.controller;

import com.samtuga.schoolmanagement.model.Teacher;
import com.samtuga.schoolmanagement.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @PostMapping("/admin/add")
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher){
        return new ResponseEntity<Teacher>(teacherService.addTeacher(teacher), HttpStatus.CREATED);
    }

    @GetMapping("/admin/view")
    public List<Teacher> viewTeachers(){
        return teacherService.getTeacher();
    }

    @DeleteMapping("/admin/remove/{id}")
    public void removeTeacher(@PathVariable("id") long id){
        teacherService.removeTeacher(id);
    }

    @PutMapping("/admin/update/{id}")
    public void updateTeacher(@PathVariable("id") long id, @RequestBody Teacher teacher){
        teacherService.updateTeacher(id,teacher);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Teacher> viewProfile(@PathVariable("id") long id){
        return new ResponseEntity<Teacher>(teacherService.findTeacher(id),HttpStatus.OK);
    }
}
