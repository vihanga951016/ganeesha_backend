package com.ganeesha.backend.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student_courses")
public class StudentCoursesBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "studentId")
    private StudentBean studentBean;
    @OneToOne
    @JoinColumn(name = "courseId")
    private CourseBean courseBean;

    public StudentCoursesBean(StudentBean studentBean, CourseBean courseBean) {
        this.studentBean = studentBean;
        this.courseBean = courseBean;
    }
}
