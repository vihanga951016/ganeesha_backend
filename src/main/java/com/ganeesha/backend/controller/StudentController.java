package com.ganeesha.backend.controller;

import com.ganeesha.backend.beans.StudentBean;
import com.ganeesha.backend.responses.AddCoursesRequestBean;
import com.ganeesha.backend.responses.AddStudentRequestBean;
import com.ganeesha.backend.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public ResponseEntity addNewStudent(@RequestBody AddStudentRequestBean addStudentRequestBean){
        return studentService.addStudent(addStudentRequestBean);
    }

    @PostMapping("/update")
    public ResponseEntity updateStudent(@RequestBody StudentBean studentBean){
        return studentService.updateStudent(studentBean);
    }

    @GetMapping("/get-all")
    public ResponseEntity getAllStudents(){
        return studentService.getAllStudents();
    }

    @DeleteMapping("/delete/student/{id}")
    public ResponseEntity deleteStudent(@PathVariable("id")Integer id){
        return studentService.deleteStudent(id);
    }

    @GetMapping("/get/student/{id}")
    public ResponseEntity getStudentById(@PathVariable("id")Integer id){
        return studentService.getStudentById(id);
    }

    @PostMapping("/add/course")
    public ResponseEntity addCoursesToStudent(@RequestBody AddCoursesRequestBean requestBean){
        return studentService.addCoursesToStudent(requestBean);
    }
}
