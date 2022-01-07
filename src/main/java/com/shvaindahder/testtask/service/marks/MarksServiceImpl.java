package com.shvaindahder.testtask.service.marks;

import com.shvaindahder.testtask.dto.response.MarkDTO;
import com.shvaindahder.testtask.dto.response.MarksOfStudentBySubjectResponse;
import com.shvaindahder.testtask.entity.Marks;
import com.shvaindahder.testtask.entity.Student;
import com.shvaindahder.testtask.entity.Subject;
import com.shvaindahder.testtask.exceptions.MarkNotFoundException;
import com.shvaindahder.testtask.repository.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MarksServiceImpl implements MarksService {

    private final MarksRepository marksRepository;

    public MarksServiceImpl(MarksRepository marksRepository) {
        this.marksRepository = marksRepository;
    }

    @Override
    public MarkDTO getById(Long id) throws MarkNotFoundException {
        return toDTO(marksRepository.findById(id).orElseThrow(MarkNotFoundException::new));
    }

    @Override
    public List<MarksOfStudentBySubjectResponse> getMarksOfStudent(Student student) {
        List<Marks> studentMarks = marksRepository.findMarksByStudent(student);
        Map<Subject, List<Marks>> splitMarksBySubject =
                studentMarks
                        .stream()
                        .collect(Collectors.groupingBy(Marks::getSubject));

        List<MarksOfStudentBySubjectResponse> marksOfStudentBySubjectResponses = new ArrayList<>();

        for (Subject subject : splitMarksBySubject.keySet()) {
            marksOfStudentBySubjectResponses.add(
                    MarksOfStudentBySubjectResponse.of(splitMarksBySubject.get(subject))
            );
        }

        return marksOfStudentBySubjectResponses;
    }

    @Override
    public List<MarkDTO> getMarksOfStudentBySubject(Student student, Subject subject) {
        List<Marks> studentMarks = marksRepository.findMarksByStudentAndSubjectOrderByDateTimeDesc(student, subject);
        return studentMarks.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public MarkDTO save(Marks mark) {
        marksRepository.save(mark);
        return toDTO(mark);
    }

    @Override
    public MarkDTO toDTO(Marks object) {
        MarkDTO markDTO = new MarkDTO();

        markDTO.setMark(object.getMark());
        markDTO.setDateTime(object.getDateTime());
        return markDTO;
    }

    @Override
    public Marks fromDTO(MarkDTO markDTO) {
        return null;
    }
}
