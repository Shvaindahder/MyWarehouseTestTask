package com.shvaindahder.testtask.service.report;

import com.shvaindahder.testtask.dto.StudentFilter;
import com.shvaindahder.testtask.dto.response.report.ReportResponse;

public interface ReportService {
    ReportResponse generateReport(StudentFilter filter);
}
