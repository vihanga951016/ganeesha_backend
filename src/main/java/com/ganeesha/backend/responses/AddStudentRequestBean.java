package com.ganeesha.backend.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentRequestBean {

    private String name;
//    @Temporal(TemporalType.DATE)
//    private Date regDate;
    private String nic;

    private List<Integer> courses;
}
