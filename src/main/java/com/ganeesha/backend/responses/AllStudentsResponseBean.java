package com.ganeesha.backend.responses;
import com.ganeesha.backend.beans.StudentCoursesBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllStudentsResponseBean {

    private Integer id;
    private String name;
    private String regNo;
    private Date regDate;
    private String nic;

    private List<StudentCoursesBean> studentCourses;
}
