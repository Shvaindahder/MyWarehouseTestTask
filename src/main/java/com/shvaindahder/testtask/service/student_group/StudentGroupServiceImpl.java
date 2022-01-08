package com.shvaindahder.testtask.service.student_group;

import com.shvaindahder.testtask.entity.StudentGroup;
import com.shvaindahder.testtask.exception.StudentsGroupNotFoundException;
import com.shvaindahder.testtask.repository.StudentGroupRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentGroupServiceImpl implements StudentGroupService {
    private final StudentGroupRepository studentGroupRepository;

    public StudentGroupServiceImpl(StudentGroupRepository studentGroupRepository) {
        this.studentGroupRepository = studentGroupRepository;
    }

    @Override
    public StudentGroup getByName(String name) throws StudentsGroupNotFoundException {
        return studentGroupRepository.findByName(name).orElseThrow(StudentsGroupNotFoundException::new);
    }
}
