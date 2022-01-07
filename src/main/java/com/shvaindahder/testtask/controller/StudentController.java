package com.shvaindahder.testtask.controller;

import com.shvaindahder.testtask.dto.StudentDTO;
import com.shvaindahder.testtask.dto.StudentFilter;
import com.shvaindahder.testtask.dto.response.StudentsResponse;
import com.shvaindahder.testtask.entity.Student;
import com.shvaindahder.testtask.exceptions.StudentNotFoundException;
import com.shvaindahder.testtask.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("")
    ResponseEntity<StudentsResponse> getStudents(@RequestParam(required = false) StudentFilter filter) {
        StudentsResponse students = studentService.getAllStudents(filter);
        return ResponseEntity.ok(students);
    }
    
    @GetMapping("{userId}")
    ResponseEntity<StudentDTO> getStudent(@PathVariable Long userId) throws StudentNotFoundException {
        StudentDTO student = studentService.getById(userId);
        return ResponseEntity.ok(student);
    }

    @PostMapping(value = "")
    ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO) {
        try {
            studentService.getById(studentDTO.getId());
            return ResponseEntity.status(303).body(studentDTO);
        } catch (StudentNotFoundException | IllegalArgumentException ex) {
            studentService.save(studentDTO);
            return ResponseEntity.status(201).body(studentDTO);
        }
    }

    @PutMapping(value = "{userId}")
    ResponseEntity<Student> updateStudent(@PathVariable Long userId, @RequestBody StudentDTO studentDTO) throws StudentNotFoundException {
        studentService.update(userId, studentDTO);
        return ResponseEntity.ok(studentService.fromDTO(studentDTO));
    }

    @PatchMapping(value = "{userId}")
    ResponseEntity<StudentDTO> patchStudent(@PathVariable Long userId, StudentDTO studentDTO) {
        try {
            StudentDTO student = studentService.patch(userId, studentDTO);
            return ResponseEntity.status(204).body(student);
        } catch (StudentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "{userId}")
    ResponseEntity<?> deleteStudent(@PathVariable Long userId) throws StudentNotFoundException{
        StudentDTO studentDTO = studentService.getById(userId);
        boolean status = studentService.delete(studentDTO);

        if (status) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(202).build();
        }
    }
}
