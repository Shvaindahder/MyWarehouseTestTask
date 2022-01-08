package com.shvaindahder.testtask.controller;

import com.shvaindahder.testtask.dto.request.MarkRequest;
import com.shvaindahder.testtask.dto.response.MarkDTO;
import com.shvaindahder.testtask.entity.Mark;
import com.shvaindahder.testtask.entity.Student;
import com.shvaindahder.testtask.entity.Subject;
import com.shvaindahder.testtask.exception.MarkNotFoundException;
import com.shvaindahder.testtask.exception.StudentNotFoundException;
import com.shvaindahder.testtask.exception.SubjectNotFoundException;
import com.shvaindahder.testtask.service.marks.MarksService;
import com.shvaindahder.testtask.service.student.StudentService;
import com.shvaindahder.testtask.service.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/marks")
public class MarksController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private MarksService marksService;

    @GetMapping("")
    public ResponseEntity<List<MarkDTO>> getMarksOfStudent(
            @RequestParam Long subjectId,
            @RequestParam Long studentId) throws StudentNotFoundException, SubjectNotFoundException {
        Subject subject = subjectService.fromDTO(subjectService.getById(subjectId));
        Student student = studentService.fromDTO(studentService.getById(studentId));
        List<MarkDTO> marks = marksService.getMarksOfStudentBySubject(student, subject);
        return ResponseEntity.ok(marks);
    }

    @GetMapping("{markId}")
    public ResponseEntity<MarkDTO> getMark(@PathVariable Long markId) throws MarkNotFoundException {
        return ResponseEntity.ok(marksService.getById(markId));
    }

    @PostMapping("")
    public ResponseEntity<MarkDTO> setMark(
            @RequestBody MarkRequest markRequest
    ) throws StudentNotFoundException, SubjectNotFoundException {
        Mark mark = new Mark();

        mark.setStudent(
                studentService.fromDTO(
                        studentService.getById(markRequest.getStudentId())
                )
        );
        mark.setSubject(
                subjectService.fromDTO(
                        subjectService.getById(markRequest.getSubjectId())
                )
        );
        mark.setMark(markRequest.getMark());
        mark.setDateTime(LocalDateTime.now());
        return ResponseEntity.status(201).body(marksService.save(mark));
    }
}
