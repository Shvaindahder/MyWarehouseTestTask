package com.shvaindahder.testtask.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Marks {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @NotNull
    private Student student;

    @ManyToOne
    @NotNull
    private Subject subject;

    @Basic
    private LocalDateTime dateTime;

    @Min(2)
    @Max(5)
    private int mark;

    public Marks(Student student, Subject subject) {
        this.student = student;
        this.subject = subject;
    }
}
