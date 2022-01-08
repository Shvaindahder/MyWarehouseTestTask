package com.shvaindahder.testtask.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@NoArgsConstructor
public class StudentReportResponse {
    private String surname;

    private String groupName;

    private List<AvgMarkOfSubject> subjects;

    @Getter
    @Setter
    public static class AvgMarkOfSubject {
        private String subject;

        private double avgMark;

        AvgMarkOfSubject(String subject, double avgMark) {
            this.subject = subject;
            this.avgMark = avgMark;
        }

        public static double calculateAverageMark(Stream<Short> marks) {
            int sum = 0;

            List<Short> marksList = marks.collect(Collectors.toList());

            for (int mark : marksList) {
                sum += mark;
            }

            return marksList.size() == 0? 0: (double) sum / marksList.size();
        }
    }
}
