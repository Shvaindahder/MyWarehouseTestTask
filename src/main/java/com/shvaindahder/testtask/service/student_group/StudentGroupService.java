package com.shvaindahder.testtask.service.student_group;

import com.shvaindahder.testtask.entity.StudentGroup;
import com.shvaindahder.testtask.exception.StudentsGroupNotFoundException;

public interface StudentGroupService {
    StudentGroup getByName(String name) throws StudentsGroupNotFoundException;
}
