package com.shvaindahder.testtask.repository;

import com.shvaindahder.testtask.entity.StudentGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentGroupRepository extends CrudRepository<StudentGroup, Long> {
    Optional<StudentGroup> findByName(String name);
}
