package com.shvaindahder.testtask.service.marks;

import com.shvaindahder.testtask.dto.response.MarkDTO;
import com.shvaindahder.testtask.dto.response.MarksOfStudentBySubjectResponse;
import com.shvaindahder.testtask.entity.Marks;
import com.shvaindahder.testtask.entity.Student;
import com.shvaindahder.testtask.entity.Subject;
import com.shvaindahder.testtask.exceptions.MarkNotFoundException;
import com.shvaindahder.testtask.service.Service;

import java.util.List;

public interface MarksService extends Service<MarkDTO, Marks> {
    MarkDTO getById(Long id) throws MarkNotFoundException;

    MarkDTO save(Marks mark);

    List<MarksOfStudentBySubjectResponse> getMarksOfStudent(Student student);

    List<MarkDTO> getMarksOfStudentBySubject(Student student, Subject subject);
}
