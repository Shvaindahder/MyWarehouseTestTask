package com.shvaindahder.testtask.controller;

import com.shvaindahder.testtask.dto.StudentDTO;
import com.shvaindahder.testtask.dto.response.StudentsResponse;
import com.shvaindahder.testtask.entity.Student;
import com.shvaindahder.testtask.exceptions.StudentNotFoundException;
import com.shvaindahder.testtask.service.student.StudentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("")
    StudentsResponse getStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Student> addStudent(@RequestBody StudentDTO studentDTO) {
        try {
            studentService.getById(studentDTO.getId());
            return ResponseEntity.status(303).body(studentService.fromDTO(studentDTO));
        } catch (StudentNotFoundException studentNotFoundException) {
            studentService.save(studentDTO);
            return ResponseEntity.status(201).body(studentService.fromDTO(studentDTO));
        }
    }

    @PutMapping(value = "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Student> updateStudent(Long userId, @RequestBody StudentDTO studentDTO) {

    }

}
