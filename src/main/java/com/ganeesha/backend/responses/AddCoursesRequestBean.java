package com.ganeesha.backend.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCoursesRequestBean {
    private Integer id;
    private List<Integer> courses;
}
