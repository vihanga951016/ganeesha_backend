package com.ganeesha.backend.controller;

import com.ganeesha.backend.beans.LectureBean;
import com.ganeesha.backend.services.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/lecture")
public class LectureController {

    @Autowired
    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @PostMapping("/add")
    public ResponseEntity addNewLecture(@RequestBody LectureBean lectureBean){
        return lectureService.addLecture(lectureBean);
    }

    @PostMapping("/update")
    public ResponseEntity updateLecture(@RequestBody LectureBean lectureBean){
        return lectureService.updateLecture(lectureBean);
    }

    @DeleteMapping("/delete/lecture/{id}")
    public ResponseEntity deleteLecture(@PathVariable Integer id){
        return lectureService.deleteLecture(id);
    }

    @GetMapping("/get-all")
    public ResponseEntity getAllLectures(){
        return lectureService.getAllLectures();
    }

    @GetMapping("/get/lecture/{id}")
    public ResponseEntity getLectureById(@PathVariable("id") Integer id){
        return lectureService.getLectureById(id);
    }

    @GetMapping("/get-salary/lecture/{id}")
    public ResponseEntity getLectureSalary(@PathVariable("id") Integer id){
        return lectureService.getLectureSalary(id);
    }
}
