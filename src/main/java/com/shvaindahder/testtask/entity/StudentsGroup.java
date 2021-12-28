package com.shvaindahder.testtask.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class StudentsGroup {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "group")
    Set<Student> students = new HashSet<>();

    public StudentsGroup(String name) {
        this.name = name;
    }
}
