package com.shvaindahder.testtask.dto;

import com.shvaindahder.testtask.dto.response.MarksOfStudentBySubjectResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class StudentDTO {
    private Long id;

    private String surname;

    private String groupName;

    private Set<String> subjects = new HashSet<>();

    private List<MarksOfStudentBySubjectResponse> marks = new ArrayList<>();
}
