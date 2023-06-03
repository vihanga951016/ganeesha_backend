package com.ganeesha.backend.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course")
public class CourseBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String courseId;
    private String name;
    private Integer durationMonths;
    @OneToOne
    @JoinColumn(name = "lectureId")
    private LectureBean lectureBean;
    private String description;
    private boolean status;

    public CourseBean(Integer id) {
        this.id = id;
    }
}
