package com.ganeesha.backend.controller;

import com.ganeesha.backend.beans.CourseBean;
import com.ganeesha.backend.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/add")
    public ResponseEntity addNewCourse(@RequestBody CourseBean courseBean) {
        return courseService.addCourse(courseBean);
    }

    @PostMapping("/update")
    public ResponseEntity updateCourse(@RequestBody CourseBean courseBean) {
        return courseService.updateCourse(courseBean);
    }

    @DeleteMapping("/delete/course/{id}")
    public ResponseEntity deleteCourseById(@PathVariable("id") Integer id) {
        return courseService.deleteCourse(id);
    }

    @GetMapping("/get-all")
    public ResponseEntity getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/get/course/{id}")
    public ResponseEntity getCourseById(@PathVariable("id") Integer id) {
        return courseService.getCourseById(id);
    }
}
