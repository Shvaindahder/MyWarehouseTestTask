package com.shvaindahder.testtask.service.student_group;

import com.shvaindahder.testtask.entity.StudentGroup;
import com.shvaindahder.testtask.exception.StudentsGroupNotFoundException;
import com.shvaindahder.testtask.repository.StudentGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentGroupServiceImpl implements StudentGroupService {
    private final StudentGroupRepository studentGroupRepository;

    public StudentGroupServiceImpl(StudentGroupRepository studentGroupRepository) {
        this.studentGroupRepository = studentGroupRepository;
    }

    @Override
    public List<StudentGroup> getAll() {
        Iterable<StudentGroup> studentGroups = studentGroupRepository.findAll();
        return StreamSupport.stream(studentGroups.spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public StudentGroup getByName(String name) throws StudentsGroupNotFoundException {
        return studentGroupRepository.findByName(name).orElseThrow(StudentsGroupNotFoundException::new);
    }
}
