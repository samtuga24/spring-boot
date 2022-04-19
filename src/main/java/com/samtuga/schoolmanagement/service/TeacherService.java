package com.samtuga.schoolmanagement.service;

import com.samtuga.schoolmanagement.model.Grade;
import com.samtuga.schoolmanagement.model.Student;
import com.samtuga.schoolmanagement.model.Subject;
import com.samtuga.schoolmanagement.model.Teacher;
import com.samtuga.schoolmanagement.repository.GradeRepository;
import com.samtuga.schoolmanagement.repository.SubjectRepository;
import com.samtuga.schoolmanagement.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
@Service
public class TeacherService {
    private final String TEACHER_EXISTS_ERROR = "teacher with email %s already exists";
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public Teacher addTeacher(Teacher teacher){
        teacherRepository.findByEmail(teacher.getEmail()).ifPresent(param->{
            throw new IllegalStateException(String.format(TEACHER_EXISTS_ERROR,teacher.getEmail()));
        });

        Teacher newTeacher = new Teacher();
        newTeacher.setFirstName(teacher.getFirstName());
        newTeacher.setLastName(teacher.getLastName());
        newTeacher.setEmail(teacher.getEmail());
        newTeacher.setPassword(bCryptPasswordEncoder.encode(teacher.getPassword()));
        newTeacher.setDob(teacher.getDob());
        newTeacher.setAge(Period.between(teacher.getDob(),LocalDate.now()).getYears());
        newTeacher.setLocation(teacher.getLocation());
        newTeacher.setPhoneNumber(teacher.getPhoneNumber());

        Set<Grade> newGrade = new HashSet<>();

        newGrade.addAll(teacher.getGrades());
        newGrade.stream().forEach( g -> {
            Grade grade = gradeRepository.findByGradeName(g.getGradeName()).orElse(null);
            if(null == grade){
                grade =new Grade();
            }
            grade.setGradeName(g.getGradeName());
            });

        Set<Subject> newSubject = new HashSet<>();
        newSubject.addAll(teacher.getSubjects());
        newSubject.stream().forEach( s -> {
            Subject sub = subjectRepository.findSubjectById(s.getId()).orElse(null);
            if(null == sub){
                sub =new Subject();
            }
            sub.setSubjectName(s.getSubjectName());
        });
            newTeacher.setGrades(newGrade);
            newTeacher.setSubjects(newSubject);

        return teacherRepository.save(newTeacher);
    }

    public void removeTeacher(long id){
        teacherRepository.findTeacherById(id)
                .orElseThrow(()-> new IllegalStateException(""));
        teacherRepository.deleteById(id);

    }

    public void updateTeacher(long id, Teacher teacher){
        teacherRepository.findTeacherById(id)
                .orElseThrow(()->new IllegalStateException(""));
        teacher.setId(id);
        teacherRepository.save(teacher);

    }

    public List<Teacher> getTeacher(){
        return teacherRepository.findAll();
    }

    public Teacher findTeacher(long id){
        return teacherRepository.findTeacherById(id)
                .orElseThrow(()->new IllegalStateException("teacher not found"));
    }
}
