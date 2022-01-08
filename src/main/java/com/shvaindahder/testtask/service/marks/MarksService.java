package com.shvaindahder.testtask.service.marks;

import com.shvaindahder.testtask.dto.response.MarkDTO;
import com.shvaindahder.testtask.dto.response.MarksOfStudentBySubjectResponse;
import com.shvaindahder.testtask.entity.Mark;
import com.shvaindahder.testtask.entity.Student;
import com.shvaindahder.testtask.entity.Subject;
import com.shvaindahder.testtask.exception.MarkNotFoundException;
import com.shvaindahder.testtask.service.Service;

import java.util.List;

public interface MarksService extends Service<MarkDTO, Mark> {
    MarkDTO getById(Long id) throws MarkNotFoundException;

    MarkDTO save(Mark mark);

    List<MarksOfStudentBySubjectResponse> getMarksOfStudent(Student student);

    List<MarkDTO> getMarksOfStudentBySubject(Student student, Subject subject);
}
