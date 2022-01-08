package com.shvaindahder.testtask.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentFilter {
    private String surname;

    private String groupName;

    private String subjectName;

    private Double minAvgMark;

    public StudentFilter(String surname) {
        this.surname = surname;
    }
}
