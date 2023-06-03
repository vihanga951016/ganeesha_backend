package com.ganeesha.backend.services;

import com.ganeesha.backend.beans.StudentBean;
import com.ganeesha.backend.responses.AddCoursesRequestBean;
import com.ganeesha.backend.responses.AddStudentRequestBean;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {

    ResponseEntity addStudent(AddStudentRequestBean addStudentRequestBean);

    ResponseEntity updateStudent(StudentBean studentBean);

    ResponseEntity deleteStudent(Integer id);

    ResponseEntity getAllStudents();

    ResponseEntity getStudentById(Integer id);

    ResponseEntity addCoursesToStudent(AddCoursesRequestBean requestBean);
}
