package com.shvaindahder.testtask.dto.response.report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReportResponse {
    private List<StudentGroupReport> studentGroups;
}
