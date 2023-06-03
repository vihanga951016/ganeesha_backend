package com.ganeesha.backend.services.impls;

import com.ganeesha.backend.beans.LectureBean;
import com.ganeesha.backend.repositories.LectureRepository;
import com.ganeesha.backend.responses.SalaryResponseBean;
import com.ganeesha.backend.services.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class LectureServiceImpl implements LectureService {

    @Autowired
    private final LectureRepository lectureRepository;

    public LectureServiceImpl(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @Override
    public ResponseEntity addLecture(LectureBean lectureBean) {

        if(lectureBean.getExperience() >= 3) {
            lectureBean.setEligible(true);
        }

        LectureBean newBean = lectureRepository.save(lectureBean);

        String lectureId = "L" + Common.formatId(newBean.getId());

        newBean.setLectureId(lectureId);

        return ResponseEntity.ok().body(lectureRepository.save(newBean));
    }

    @Override
    public ResponseEntity updateLecture(LectureBean lectureBean) {
        LectureBean lecture = lectureRepository.getLectureById(lectureBean.getId());

        if(lecture == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Lecture not found");
        }

        lectureBean.setLectureId(lecture.getLectureId());

        if(lectureBean.getExperience() >= 3) {
            lectureBean.setEligible(true);
        }

        return ResponseEntity.ok().body(lectureRepository.save(lectureBean));
    }

    @Override
    public ResponseEntity deleteLecture(Integer id) {

        LectureBean lecture = lectureRepository.getLectureById(id);

        if(lecture == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Lecture not found");
        }

        lectureRepository.deleteById(id);

        return ResponseEntity.ok().body(true);
    }

    @Override
    public ResponseEntity getAllLectures() {
        return ResponseEntity.ok().body(lectureRepository.findAll());
    }

    @Override
    public ResponseEntity getLectureById(Integer id) {
        LectureBean lecture = lectureRepository.getLectureById(id);

        if(lecture == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Lecture not found");
        }
        return ResponseEntity.ok().body(lecture);
    }

    @Override
    public ResponseEntity getLectureSalary(Integer id) {
        LectureBean lecture = lectureRepository.getLectureById(id);

        if(lecture == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Lecture not found");
        }

        String hoursString = new SimpleDateFormat("HH").format(lecture.getHoursWorked());
        String minString = new SimpleDateFormat("mm").format(lecture.getHoursWorked());

        int hoursInt = Integer.parseInt(hoursString);
        int minInt = Integer.parseInt(minString);

        double hoursFromSec = hoursInt * 3600;
        double minFromSec = minInt * 60;

        double totalTime = hoursFromSec + minFromSec;

        double hours = totalTime / 3600;

        double payment = lecture.getRate() * hours;

        double totalPayment;

        SalaryResponseBean responseBean = new SalaryResponseBean();

        responseBean.setName(lecture.getName());

        if(lecture.getExperience() >= 3) {
            totalPayment = payment + 500;
        } else if(lecture.getExperience() > 3 && lecture.getExperience() <= 5) {
            totalPayment = payment + 1000;
        } else if(lecture.getExperience() > 5) {
            totalPayment = payment + 2000;
        } else {
            totalPayment = payment;
        }

        responseBean.setPayment(totalPayment);

        return ResponseEntity.ok().body(responseBean);
    }

}
