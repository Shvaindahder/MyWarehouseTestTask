package com.shvaindahder.testtask.service.report;

import com.shvaindahder.testtask.dto.StudentFilter;
import com.shvaindahder.testtask.dto.response.report.ReportResponse;
import com.shvaindahder.testtask.dto.response.report.StudentGroupReport;
import com.shvaindahder.testtask.dto.response.report.StudentReport;
import com.shvaindahder.testtask.dto.response.report.SubjectReport;
import com.shvaindahder.testtask.entity.StudentGroup;
import com.shvaindahder.testtask.service.student.StudentService;
import com.shvaindahder.testtask.service.student_group.StudentGroupService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private StudentGroupService studentGroupService;

    private StudentService studentService;

    public ReportServiceImpl(StudentGroupService studentGroupService, StudentService studentService) {
        this.studentGroupService = studentGroupService;
        this.studentService = studentService;
    }

    @Override
    public ReportResponse generateReport(StudentFilter filter) {
        ReportResponse reportResponse = new ReportResponse();
        StudentGroupReport studentGroupReport = new StudentGroupReport();
        StudentReport studentReport = new StudentReport();
        SubjectReport subjectReport = new SubjectReport();

        if (filter.getGroupName() == null) {
            for (StudentGroup studentGroup : studentGroupService.getAll()) {
                studentGroupReport.setName(studentGroup.getName());

            }
        }

        return reportResponse;
    }
}
