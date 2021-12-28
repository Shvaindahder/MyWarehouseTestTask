package com.shvaindahder.testtask.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
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
