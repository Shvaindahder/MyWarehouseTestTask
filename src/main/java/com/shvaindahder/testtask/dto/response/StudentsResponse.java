package com.shvaindahder.testtask.dto.response;

import com.shvaindahder.testtask.dto.StudentDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class StudentsResponse {
    Set<StudentDTO> students;

    private StudentsResponse() {
    }

    public static StudentsResponse of(Set<StudentDTO> students) {
        StudentsResponse studentsResponse = new StudentsResponse();
        studentsResponse.setStudents(students);
        return studentsResponse;
    }
}
