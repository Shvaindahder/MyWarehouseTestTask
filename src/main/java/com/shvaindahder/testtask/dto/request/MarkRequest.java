package com.shvaindahder.testtask.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MarkRequest {
    private Long studentId;

    private Long subjectId;

    private short mark;
}
