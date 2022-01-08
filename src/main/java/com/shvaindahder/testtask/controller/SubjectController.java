package com.shvaindahder.testtask.controller;

import com.shvaindahder.testtask.dto.SubjectDTO;
import com.shvaindahder.testtask.exception.StudentsGroupNotFoundException;
import com.shvaindahder.testtask.exception.SubjectNotFoundException;
import com.shvaindahder.testtask.service.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @GetMapping("")
    public ResponseEntity<Set<SubjectDTO>> getAllSubjects(@RequestParam(required = false, name = "group-name") String groupName) throws StudentsGroupNotFoundException {
        if (groupName != null) {
            return ResponseEntity.ok(subjectService.getSubjectsByGroup(groupName));
        }
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @GetMapping({"subjectId"})
    public ResponseEntity<SubjectDTO> getSubject(@PathVariable Long subjectId) throws SubjectNotFoundException {
        return ResponseEntity.ok(subjectService.getById(subjectId));
    }

    @PostMapping("")
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody SubjectDTO subjectDTO) {
        subjectService.save(subjectDTO);
        return ResponseEntity.ok(subjectDTO);
    }

    @PutMapping("{subjectId}")
    public ResponseEntity<?> updateSubject(@PathVariable Long subjectId, @RequestBody SubjectDTO subjectDTO) throws SubjectNotFoundException {
        if (subjectService.update(subjectId, subjectDTO)) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(201).build();
    }

    @PatchMapping("{subjectId}")
    public ResponseEntity<?> patchSubject(@PathVariable Long subjectId, @RequestBody SubjectDTO subjectDTO) {
        try {
            SubjectDTO subject = subjectService.patchById(subjectId, subjectDTO);
            return ResponseEntity.ok(subjectDTO);
        } catch (SubjectNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{subjectId}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long subjectId) throws SubjectNotFoundException {
        boolean status = subjectService.delete(
                subjectService.getById(subjectId)
        );

        if (status) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(202).build();
        }
    }
}
