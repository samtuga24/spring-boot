package com.samtuga.schoolmanagement;

import com.samtuga.schoolmanagement.model.Admin;
import com.samtuga.schoolmanagement.model.Grade;
import com.samtuga.schoolmanagement.model.Subject;
import com.samtuga.schoolmanagement.model.Teacher;
import com.samtuga.schoolmanagement.repository.AdminRepository;
import com.samtuga.schoolmanagement.repository.GradeRepository;
import com.samtuga.schoolmanagement.repository.SubjectRepository;
import com.samtuga.schoolmanagement.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@Slf4j
public class SchoolManagementApplication {
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private GradeRepository gradeRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SchoolManagementApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner testInput() {
//		return args -> {
//
////			Teacher teacher = new Teacher();
////			teacher.setFirstName("Samuel");
////			teacher.setLastName("Owusu");
////			teacher.setEmail("owusu@gmail.com");
////			teacher.setPassword("12345");
//////			teacher.setDob(1993-08-10);
//////			teacher.setAge(23);
////			teacher.setLocation("Accra");
////			teacher.setPhoneNumber("024");
////			teacher.setPhoneNumber("024");
////
////			Grade grade = new Grade();
////			grade.setGradeName("six");
////			List<Grade> grades = Collections.singletonList(grade);
////			teacher.setGrades(grades);
////
////			Subject subject = new Subject();
////			subject.setSubjectName("Maths");
////			List<Subject> subjects = Collections.singletonList(subject);
////			teacher.setSubjects(subjects);
////			teacherRepository.save(teacher);
////
////			log.info(grade.getGradeName());
//			adminRepository.save(new Admin("Samuel","Owusu","owusu684@gmail.com",
//					bCryptPasswordEncoder.encode("12345"),"GT-302-0045",
//					"0249628340", new File("/sth/.png")));
//		};
////
//	}
}

