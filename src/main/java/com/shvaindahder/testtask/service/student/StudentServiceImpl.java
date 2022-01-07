package com.shvaindahder.testtask.service.student;

import com.shvaindahder.testtask.dto.StudentDTO;
import com.shvaindahder.testtask.dto.StudentFilter;
import com.shvaindahder.testtask.dto.response.StudentsResponse;
import com.shvaindahder.testtask.entity.Student;
import com.shvaindahder.testtask.entity.Subject;
import com.shvaindahder.testtask.exceptions.StudentNotFoundException;
import com.shvaindahder.testtask.exceptions.StudentsGroupNotFoundException;
import com.shvaindahder.testtask.repository.StudentRepository;
import com.shvaindahder.testtask.service.marks.MarksService;
import com.shvaindahder.testtask.service.student_group.StudentGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final MarksService marksService;

    private final StudentGroupService studentGroupService;

    public StudentServiceImpl(
            StudentRepository studentRepository,
            MarksService marksService,
            StudentGroupService studentGroupService) {
        this.studentRepository = studentRepository;
        this.marksService = marksService;
        this.studentGroupService = studentGroupService;
    }

    @Override
    public StudentDTO getById(Long id) throws StudentNotFoundException, IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException();
        return studentRepository
                .findById(id)
                .map(this::toDTO)
                .orElseThrow(StudentNotFoundException::new);
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
    public StudentsResponse getAllStudents(StudentFilter filter) {
        Iterable<Student> students = studentRepository.findAll();
        Set<StudentDTO> studentDTOs = new HashSet<>();

        for (Student student: students) {
            studentDTOs.add(toDTO(student));
        }

        if (filter != null) {
            return StudentsResponse.of(studentDTOs
                    .stream()
                    .filter(
                            (StudentDTO studentDTO) -> studentDTO
                                    .getSurname()
                                    .equals(filter.getSurname())
                    ).collect(Collectors.toSet()));
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
        if (student.getId() != null) {
            studentRepository.delete(student);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(Long id, StudentDTO newStudent) {
        Student prev;

        try {
            prev = studentRepository.findById(id).orElseThrow(NoSuchElementException::new);
            studentRepository.delete(prev);
            studentRepository.save(fromDTO(newStudent));
            return true;
        } catch (NoSuchElementException exception) {
            studentRepository.save(fromDTO(newStudent));
            return false;
        }
    }

    @Override
    public StudentDTO patch(Long id, StudentDTO studentDTO) throws StudentNotFoundException {
        Optional<Student> prev = studentRepository.findById(id);

        if (prev.isEmpty()) throw new StudentNotFoundException();

        Student student = prev.get();

        if (studentDTO.getSurname() != null) {
            student.setSurname(studentDTO.getSurname());
        }

        if (studentDTO.getGroupName() != null) {
            try {
                student.setGroup(studentGroupService.getByName(studentDTO.getGroupName()));
            } catch (StudentsGroupNotFoundException ex) {
                ex.printStackTrace();
            }
        }

        return toDTO(studentRepository.save(student));
    }

    @Override
    public StudentDTO toDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setSurname(student.getSurname());
        studentDTO.setGroupName(student.getGroup().getName());
        studentDTO.setSubjects(
                student
                        .getGroup()
                        .getSubjects()
                        .stream()
                        .map(Subject::getName)
                        .collect(Collectors.toSet())
        );
        studentDTO.setMarks(
                marksService.getMarksOfStudent(student)
        );
        return studentDTO;
    }

    @Override
    public Student fromDTO(StudentDTO studentDTO) {
        if (studentDTO.getId() == null) {
            return createFromDTO(studentDTO);
        } else {
            return studentRepository.findById(studentDTO.getId()).orElse(createFromDTO(studentDTO));
        }
    }

    private Student createFromDTO(StudentDTO studentDTO) {
        try {
            return new Student(
                    studentDTO.getSurname(),
                    studentGroupService.getByName(studentDTO.getGroupName()));
        } catch (StudentsGroupNotFoundException ex1) {
            return null;
        }
    }
}
