package com.shvaindahder.testtask.repository;

import com.shvaindahder.testtask.entity.Student;
import com.shvaindahder.testtask.entity.StudentsGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Set;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    Set<Student> findBySurname(String surname);

    Set<Student> findByGroup(StudentsGroup studentsGroup);
}
