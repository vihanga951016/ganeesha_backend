package com.ganeesha.backend.services.impls;

import com.ganeesha.backend.beans.CourseBean;
import com.ganeesha.backend.repositories.CourseRepository;
import com.ganeesha.backend.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public ResponseEntity addCourse(CourseBean courseBean) {

        CourseBean newBean = courseRepository.save(courseBean);

        String courseId = "C" + Common.formatId(newBean.getId());

        newBean.setCourseId(courseId);

        return ResponseEntity.ok().body(courseRepository.save(newBean));
    }

    @Override
    public ResponseEntity updateCourse(CourseBean courseBean) {

        CourseBean course = courseRepository.getCourseById(courseBean.getId());

        if(course == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Course not found");
        }

        courseBean.setCourseId(course.getCourseId());

        return ResponseEntity.ok().body(courseRepository.save(courseBean));
    }

    @Override
    public ResponseEntity deleteCourse(Integer id) {
        CourseBean course = courseRepository.getCourseById(id);

        if(course == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Course not found");
        }

        courseRepository.deleteById(id);

        return ResponseEntity.ok().body(true);
    }

    @Override
    public ResponseEntity getAllCourses() {
        return ResponseEntity.ok().body(courseRepository.findAll());
    }

    @Override
    public ResponseEntity getCourseById(Integer id) {
        CourseBean course = courseRepository.getCourseById(id);

        if(course == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Course not found");
        }
        return ResponseEntity.ok().body(course);
    }
}
