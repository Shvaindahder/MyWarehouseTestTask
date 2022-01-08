package com.shvaindahder.testtask.repository;

import com.shvaindahder.testtask.entity.Mark;
import com.shvaindahder.testtask.entity.Student;
import com.shvaindahder.testtask.entity.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarksRepository extends CrudRepository<Mark, Long> {
    List<Mark> findMarksByStudent(Student student);
    List<Mark> findMarksByStudentAndSubject(Student student, Subject subject);
    List<Mark> findMarksByStudentAndSubjectOrderByDateTimeDesc(Student student, Subject subject);

}
