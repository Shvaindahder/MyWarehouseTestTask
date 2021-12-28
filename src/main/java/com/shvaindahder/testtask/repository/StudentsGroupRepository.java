package com.shvaindahder.testtask.repository;

import com.shvaindahder.testtask.entity.Student;
import com.shvaindahder.testtask.entity.StudentsGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface StudentsGroupRepository extends CrudRepository<StudentsGroup, Long> {
    Set<StudentsGroup> findByName(String groupName);

    Set<StudentsGroup> findByStudentsIsContaining(Student student);
}
