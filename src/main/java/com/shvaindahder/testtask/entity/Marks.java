package com.shvaindahder.testtask.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Check(constraints = "mark >= 2 AND mark <= 5")
@Data
@NoArgsConstructor
public class Marks {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Subject subject;

    @Basic
    private LocalDateTime dateTime;

    private int mark;

    public Marks(Student student, Subject subject) {
        this.student = student;
        this.subject = subject;
    }
}
