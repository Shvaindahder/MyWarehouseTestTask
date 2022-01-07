package com.shvaindahder.testtask.dto.response;

import com.shvaindahder.testtask.entity.Marks;
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

    private static boolean checkIfManySubjectsIsMarksList(List<Marks> marks) {
        String firstMarkSubject = marks.get(0).getSubject().getName();
        if (
                marks
                        .stream()
                        .allMatch(
                                (Marks mark) -> mark.getSubject().getName().equals(firstMarkSubject)
                        )
        ) {
            return true;
//            MarksOfStudentBySubjectResponse marksOfStudentBySubjectResponse = new MarksOfStudentBySubjectResponse();
//            marksOfStudentBySubjectResponse.setSubjectName(firstMarkSubject);
//            marksOfStudentBySubjectResponse.setStudentId(marks.get(0).getStudent().getId());
//            marksOfStudentBySubjectResponse.setStudentSurname(marks.get(0).getStudent().getSurname());
//            marksOfStudentBySubjectResponse.setMarks(
//                    marks
//                            .stream()
//                            .map(
//                                    (Marks mark) -> new Mark(
//                                            mark.getMark(),
//                                            mark.getDateTime()
//                                    )
//                            ).collect(Collectors.toList())
//            );
//            return marksOfStudentBySubjectResponse;
        }

        return false;
    }

    public static MarksOfStudentBySubjectResponse of(List<Marks> marks) throws IllegalArgumentException {
        // marks should be split by subject
        if (marks.size() == 0) {
            return null;
        }

        if (checkIfManySubjectsIsMarksList(marks)) {
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
                                (Marks mark) -> new MarkDTO(
                                        mark.getMark(),
                                        mark.getDateTime()
                                )
                        ).collect(Collectors.toList())
        );
        return marksOfStudentBySubjectResponse;
    }
}
