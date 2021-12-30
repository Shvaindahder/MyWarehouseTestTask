package com.shvaindahder.testtask.service.student;

import com.shvaindahder.testtask.dto.StudentDTO;
import com.shvaindahder.testtask.dto.response.StudentsResponse;
import com.shvaindahder.testtask.entity.Student;
import com.shvaindahder.testtask.exceptions.StudentNotFoundException;
import com.shvaindahder.testtask.service.Service;

public interface StudentService extends Service<StudentDTO, Student> {
    StudentDTO getById(Long id) throws StudentNotFoundException;
    StudentsResponse getBySurname(String surname);
    StudentsResponse getAllStudents();
    StudentDTO save(StudentDTO dto);
    Boolean delete(StudentDTO dto);
    Boolean update(StudentDTO previousStudent, StudentDTO newStudent);
}
