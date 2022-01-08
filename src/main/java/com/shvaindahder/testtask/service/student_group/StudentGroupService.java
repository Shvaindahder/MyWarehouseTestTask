package com.shvaindahder.testtask.service.student_group;

import com.shvaindahder.testtask.entity.StudentGroup;
import com.shvaindahder.testtask.exception.StudentsGroupNotFoundException;

import java.util.List;

public interface StudentGroupService {
    List<StudentGroup> getAll();
    StudentGroup getByName(String name) throws StudentsGroupNotFoundException;
}
