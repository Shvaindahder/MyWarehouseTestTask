package com.shvaindahder.testtask.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MarkDTO {
    private Short mark;

    private LocalDateTime dateTime;

    public MarkDTO(Short mark, LocalDateTime dateTime) {
        this.mark = mark;
        this.dateTime = dateTime;
    }
}
