package com.shvaindahder.testtask.service.subject;

import com.shvaindahder.testtask.dto.SubjectDTO;
import com.shvaindahder.testtask.entity.Subject;
import com.shvaindahder.testtask.exceptions.StudentsGroupNotFoundException;
import com.shvaindahder.testtask.exceptions.SubjectNotFoundException;
import com.shvaindahder.testtask.service.Service;

import java.util.Set;

public interface SubjectService extends Service<SubjectDTO, Subject> {
    SubjectDTO getById(Long id) throws SubjectNotFoundException, IllegalArgumentException;

    Set<SubjectDTO> getAllSubjects();

    Set<SubjectDTO> getSubjectsByGroup(String groupName) throws StudentsGroupNotFoundException;

    Subject save(SubjectDTO subjectDTO);

    Boolean update(Long id, SubjectDTO subjectDTO);

    SubjectDTO patchById(Long id, SubjectDTO subjectDTO) throws SubjectNotFoundException;

    Boolean delete(SubjectDTO subjectDTO);
}
