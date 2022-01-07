package com.shvaindahder.testtask.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StudentGroupDTO {
    private String name;

    private List<String> students = new ArrayList<>();
}
