package com.shvaindahder.testtask.service.student;

import com.shvaindahder.testtask.dto.StudentDTO;
import com.shvaindahder.testtask.dto.StudentFilter;
import com.shvaindahder.testtask.dto.response.StudentsResponse;
import com.shvaindahder.testtask.entity.Student;
import com.shvaindahder.testtask.exception.StudentNotFoundException;
import com.shvaindahder.testtask.service.Service;

public interface StudentService extends Service<StudentDTO, Student> {
    StudentDTO getById(Long id) throws StudentNotFoundException, IllegalArgumentException;

    StudentsResponse getBySurname(String surname);

    StudentsResponse getAllStudents(StudentFilter filter);

    StudentDTO save(StudentDTO dto);

    boolean delete(StudentDTO dto);

    boolean update(Long id, StudentDTO newStudent);

    StudentDTO patch(Long id, StudentDTO newStudent) throws StudentNotFoundException;
}
