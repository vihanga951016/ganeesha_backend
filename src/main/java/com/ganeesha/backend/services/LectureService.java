package com.ganeesha.backend.services;

import com.ganeesha.backend.beans.LectureBean;
import org.springframework.http.ResponseEntity;

public interface LectureService {

    ResponseEntity addLecture(LectureBean bean);

    ResponseEntity updateLecture(LectureBean lectureBean);

    ResponseEntity deleteLecture(Integer id);

    ResponseEntity getAllLectures();

    ResponseEntity getLectureById(Integer id);

    ResponseEntity getLectureSalary(Integer id);
}
