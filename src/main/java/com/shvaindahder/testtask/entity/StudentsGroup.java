package com.shvaindahder.testtask.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
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
