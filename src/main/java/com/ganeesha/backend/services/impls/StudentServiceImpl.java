package com.ganeesha.backend.services.impls;

import com.ganeesha.backend.beans.CourseBean;
import com.ganeesha.backend.beans.StudentBean;
import com.ganeesha.backend.beans.StudentCoursesBean;
import com.ganeesha.backend.repositories.CourseRepository;
import com.ganeesha.backend.repositories.StudentCoursesRepository;
import com.ganeesha.backend.repositories.StudentRepository;
import com.ganeesha.backend.responses.AddCoursesRequestBean;
import com.ganeesha.backend.responses.AddStudentRequestBean;
import com.ganeesha.backend.responses.AllStudentsResponseBean;
import com.ganeesha.backend.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@SuppressWarnings("Duplicates")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentCoursesRepository studentCoursesRepository;

    public StudentServiceImpl(StudentRepository studentRepository,
                              CourseRepository courseRepository, StudentCoursesRepository studentCoursesRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.studentCoursesRepository = studentCoursesRepository;
    }

    @Override
    public ResponseEntity addStudent(AddStudentRequestBean requestBean) {

        StudentBean studentBean = new StudentBean();

        studentBean.setNic(requestBean.getNic());
        studentBean.setName(requestBean.getName());
        studentBean.setRegDate(new Date());

        StudentBean newBean = studentRepository.save(studentBean);

        for (Integer courseId: requestBean.getCourses()) {
            StudentCoursesBean studentCoursesBean = new StudentCoursesBean();

            studentCoursesBean.setStudentBean(new StudentBean(newBean.getId()));
            studentCoursesBean.setCourseBean(new CourseBean(courseId));

            studentCoursesRepository.save(studentCoursesBean);
        }

        String studentId = "M" + Common.formatId(newBean.getId());

        newBean.setRegNo(studentId);

        return ResponseEntity.ok().body(studentRepository.save(newBean));
    }

    @Override
    public ResponseEntity updateStudent(StudentBean studentBean) {

        StudentBean student = studentRepository.getStudentById(studentBean.getId());

        if(student == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Student not found");
        }

        studentBean.setRegNo(student.getRegNo());
        studentBean.setRegDate(student.getRegDate());

        return ResponseEntity.ok().body(studentRepository.save(studentBean));
    }

    @Override
    public ResponseEntity deleteStudent(Integer id) {
        StudentBean student = studentRepository.getStudentById(id);

        if(student == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Student not found");
        }

        List<StudentCoursesBean> coursesList = studentCoursesRepository.findAllByStudentId(id);

        for(StudentCoursesBean studentCoursesBean: coursesList) {
            studentCoursesRepository.deleteById(studentCoursesBean.getId());
        }

        studentRepository.deleteById(id);

        return ResponseEntity.ok().body(true);
    }

    @Override
    public ResponseEntity getAllStudents() {

        List<StudentBean> studentsList = studentRepository.findAll();

        AllStudentsResponseBean responseBean = new AllStudentsResponseBean();
        List<AllStudentsResponseBean> response = new ArrayList<>();

        for(StudentBean studentBean: studentsList) {
           List<StudentCoursesBean> studentCourses = studentCoursesRepository
                   .findAllByStudentId(studentBean.getId());
           responseBean.setId(studentBean.getId());
           responseBean.setName(studentBean.getName());
           responseBean.setNic(studentBean.getNic());
           responseBean.setRegNo(studentBean.getRegNo());
           responseBean.setRegDate(studentBean.getRegDate());
           responseBean.setStudentCourses(studentCourses);

           response.add(responseBean);
        }

        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity getStudentById(Integer id) {
        StudentBean student = studentRepository.getStudentById(id);

        if(student == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Student not found");
        }
        return ResponseEntity.ok().body(student);
    }

    @Override
    public ResponseEntity addCoursesToStudent(AddCoursesRequestBean requestBean) {

        StudentBean student = studentRepository.getStudentById(requestBean.getId());

        if(student == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Student not found");
        }

        for (Integer courseId: requestBean.getCourses()) {

            CourseBean courseBean = courseRepository.getCourseById(courseId);

            if(courseBean == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("course not found" + courseId);
            }

            StudentCoursesBean existingCourse = studentCoursesRepository
                    .findByStudentCourseIds(requestBean.getId(), courseId);

            if(existingCourse != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("this course is already existing" + courseId);
            }

            StudentCoursesBean studentCoursesBean = new StudentCoursesBean();

            studentCoursesBean.setStudentBean(new StudentBean(student.getId()));
            studentCoursesBean.setCourseBean(new CourseBean(courseId));

            studentCoursesRepository.save(studentCoursesBean);
        }

        return ResponseEntity.ok().body(true);
    }
}
