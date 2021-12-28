package com.shvaindahder.testtask.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String surname;

    @ManyToOne
    private StudentsGroup group;

    @ManyToMany(mappedBy = "students")
    Set<Subject> subjects = new HashSet<>();

    public Student(String surname) {
        this.surname = surname;
    }
}
