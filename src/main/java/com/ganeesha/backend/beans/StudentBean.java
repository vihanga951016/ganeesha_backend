package com.ganeesha.backend.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class StudentBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String regNo;
    private String name;
    @Temporal(TemporalType.DATE)
    @Column(name = "regDate")
    private Date regDate;
    private String nic;

    public StudentBean(Integer id) {
        this.id = id;
    }
}
