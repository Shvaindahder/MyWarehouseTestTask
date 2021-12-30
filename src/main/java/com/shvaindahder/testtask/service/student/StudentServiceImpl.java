package com.shvaindahder.testtask.service.student;

import com.shvaindahder.testtask.dto.StudentDTO;
import com.shvaindahder.testtask.dto.response.StudentsResponse;
import com.shvaindahder.testtask.entity.Student;
import com.shvaindahder.testtask.exceptions.StudentNotFoundException;
import com.shvaindahder.testtask.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public StudentDTO getById(Long id) throws StudentNotFoundException {
        return studentRepository.findById(id).map(this::toDTO).orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public StudentsResponse getBySurname(String surname) {
        return StudentsResponse.of(studentRepository
                .findBySurname(surname)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toSet())
        );
    }

    @Override
    public StudentsResponse getAllStudents() {
        Iterable<Student> students = studentRepository.findAll();
        Set<StudentDTO> studentDTOs = new HashSet<>();

        for (Student student: students) {
            studentDTOs.add(toDTO(student));
        }
        return StudentsResponse.of(studentDTOs);
    }

    @Override
    public StudentDTO save(StudentDTO dto) {
        return toDTO(studentRepository.save(fromDTO(dto)));
    }

    @Override
    public Boolean delete(StudentDTO dto) {
        Student student = fromDTO(dto);
        if (student != null) {
            studentRepository.delete(student);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(StudentDTO previousStudent, StudentDTO newStudent) {
        return null;
    }

    @Override
    public StudentDTO toDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setSurname(student.getSurname());
        return studentDTO;
    }

    @Override
    public Student fromDTO(StudentDTO studentDTO) {
        return null;
    }
}
