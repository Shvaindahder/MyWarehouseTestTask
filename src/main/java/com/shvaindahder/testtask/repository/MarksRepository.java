package com.shvaindahder.testtask.repository;

import com.shvaindahder.testtask.entity.Marks;
import com.shvaindahder.testtask.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarksRepository extends CrudRepository<Marks, Long> {
    List<Marks> findMarksByStudent(Student student);
}
