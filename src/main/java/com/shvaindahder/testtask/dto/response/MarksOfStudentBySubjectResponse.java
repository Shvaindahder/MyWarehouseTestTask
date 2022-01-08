package com.shvaindahder.testtask.dto.response;

import com.shvaindahder.testtask.entity.Mark;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class MarksOfStudentBySubjectResponse {
    private String subjectName;

    private Long studentId;

    private String studentSurname;

    private List<MarkDTO> markDTOS = new ArrayList<>();

    private MarksOfStudentBySubjectResponse() {

    }

    private static boolean isAllSubjectsSame(List<Mark> marks) {
        String firstMarkSubject = marks.get(0).getSubject().getName();

        return marks
                .stream()
                .allMatch(
                        (Mark mark) -> mark.getSubject().getName().equals(firstMarkSubject)
                );
    }

    public static MarksOfStudentBySubjectResponse of(List<Mark> marks) throws IllegalArgumentException {
        // marks should be split by subject
        if (marks.size() == 0) return null;

        if (!isAllSubjectsSame(marks)) {
            throw new IllegalArgumentException();
        }

        String firstMarkSubject = marks.get(0).getSubject().getName();

        MarksOfStudentBySubjectResponse marksOfStudentBySubjectResponse = new MarksOfStudentBySubjectResponse();
        marksOfStudentBySubjectResponse.setSubjectName(firstMarkSubject);
        marksOfStudentBySubjectResponse.setStudentId(marks.get(0).getStudent().getId());
        marksOfStudentBySubjectResponse.setStudentSurname(marks.get(0).getStudent().getSurname());
        marksOfStudentBySubjectResponse.setMarkDTOS(
                marks
                        .stream()
                        .map(
                                mark -> new MarkDTO(
                                        mark.getMark(),
                                        mark.getDateTime()
                                )
                        ).collect(Collectors.toList())
        );
        return marksOfStudentBySubjectResponse;
    }
}
