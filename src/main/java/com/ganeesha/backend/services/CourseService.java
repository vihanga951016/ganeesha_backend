package com.ganeesha.backend.services;

import com.ganeesha.backend.beans.CourseBean;
import org.springframework.http.ResponseEntity;

public interface CourseService {

    ResponseEntity addCourse(CourseBean courseBean);

    ResponseEntity updateCourse(CourseBean courseBean);

    ResponseEntity deleteCourse(Integer id);

    ResponseEntity getAllCourses();

    ResponseEntity getCourseById(Integer id);

}
