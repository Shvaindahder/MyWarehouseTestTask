package com.shvaindahder.testtask.dto.response;

import com.shvaindahder.testtask.dto.StudentDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
public class StudentsResponse {
    Set<StudentReportResponse> students = new HashSet<>();

    private StudentsResponse() {
    }

    public static StudentsResponse of(Set<StudentDTO> students) {
        StudentsResponse studentsResponse = new StudentsResponse();

        for (StudentDTO studentDTO : students) {
            StudentReportResponse studentReportResponse = new StudentReportResponse();
            studentReportResponse.setSurname(studentDTO.getSurname());
            studentReportResponse.setGroupName(studentDTO.getGroupName());

            List<MarksOfStudentBySubjectResponse> marksOfStudentBySubjectResponse = studentDTO.getMarks();
            List<String> markedSubjects = marksOfStudentBySubjectResponse
                    .stream()
                    .map(MarksOfStudentBySubjectResponse::getSubjectName)
                    .collect(Collectors.toList());

            studentReportResponse.setSubjects(
                    marksOfStudentBySubjectResponse
                            .stream()
                            .map(marks -> new StudentReportResponse.AvgMarkOfSubject(
                                    marks.getSubjectName(),
                                    StudentReportResponse.AvgMarkOfSubject.calculateAverageMark(marks
                                            .getMarkDTOS()
                                            .stream()
                                            .map(MarkDTO::getMark))))
                            .collect(Collectors.toList())
            );

            studentReportResponse.getSubjects().addAll(
                    studentDTO
                            .getSubjects()
                            .stream().filter(subject -> !markedSubjects.contains(subject))
                            .map(subject -> new StudentReportResponse.AvgMarkOfSubject(
                                    subject, 0
                            )).collect(Collectors.toList())
            );

            studentsResponse.students.add(studentReportResponse);
        }
        return studentsResponse;
    }
}
