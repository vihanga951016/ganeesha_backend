package com.ganeesha.backend.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lecture")
public class LectureBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 5)
    private String lectureId;
    private String name;
    private Integer experience;
    private Double rate;
    @JsonFormat(pattern="HH:mm:ss", timezone="Asia/Colombo")
    @Temporal(TemporalType.TIME)
    private Date hoursWorked;
    private boolean eligible;
}
