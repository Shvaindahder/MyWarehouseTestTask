package com.shvaindahder.testtask.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Check(constraints = "mark >= 2 AND mark <= 5")
@Getter
@Setter
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
    private Date date;

    private int mark;

    public Marks(Student student, Subject subject) {
        this.student = student;
        this.subject = subject;
    }
}
