package com.shvaindahder.testtask.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SubjectDTO {
    private Long id;

    private String name;

    private Set<String> studentsGroupName = new HashSet<>();
}
