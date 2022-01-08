package com.shvaindahder.testtask.service.subject;

import com.shvaindahder.testtask.dto.SubjectDTO;
import com.shvaindahder.testtask.entity.StudentGroup;
import com.shvaindahder.testtask.entity.Subject;
import com.shvaindahder.testtask.exception.StudentsGroupNotFoundException;
import com.shvaindahder.testtask.exception.SubjectNotFoundException;
import com.shvaindahder.testtask.repository.StudentGroupRepository;
import com.shvaindahder.testtask.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {


    private final SubjectRepository subjectRepository;

    private final StudentGroupRepository studentGroupRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository, StudentGroupRepository studentGroupRepository) {
        this.subjectRepository = subjectRepository;
        this.studentGroupRepository = studentGroupRepository;
    }

    @Override
    public SubjectDTO getById(Long id) throws SubjectNotFoundException, IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException();
        return toDTO(subjectRepository.findById(id).orElseThrow(SubjectNotFoundException::new));
    }

    @Override
    public Set<SubjectDTO> getAllSubjects() {
        var allSubjects = subjectRepository.findAll();
        Set<SubjectDTO> subjectDTOs = new HashSet<>();

        for (Subject subject : allSubjects) {
            subjectDTOs.add(toDTO(subject));
        }

        return subjectDTOs;
    }

    @Override
    public Set<SubjectDTO> getSubjectsByGroup(String groupName) throws StudentsGroupNotFoundException {
        return studentGroupRepository
                .findByName(groupName)
                .orElseThrow(StudentsGroupNotFoundException::new)
                .getSubjects()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public Subject save(SubjectDTO subjectDTO) {
        Subject subject = fromDTO(subjectDTO);
        if (subject.getId() == null) {
            subjectRepository.save(subject);
        }
        return subject;
    }

    @Override
    public boolean update(Long id, SubjectDTO subjectDTO) {
        boolean status = true;

        try {
            Subject prev = subjectRepository.findById(id).orElseThrow(SubjectNotFoundException::new);
            subjectRepository.delete(prev);
        } catch (SubjectNotFoundException ex) {
            status = false;
        } finally {
            subjectDTO.setId(null);
            save(subjectDTO);
        }

        return status;
    }

    @Override
    public SubjectDTO patchById(Long id, SubjectDTO subjectDTO) throws SubjectNotFoundException {
        System.out.println("Patching");
        System.out.println(subjectDTO.getName());
        Subject subject = subjectRepository.findById(id).orElseThrow(SubjectNotFoundException::new);

        if (subjectDTO.getName() != null) {
            subject.setName(subjectDTO.getName());
        }

        if (subjectDTO.getStudentsGroupName() != null) {
            Set<Optional<StudentGroup>> studentGroups = subjectDTO
                    .getStudentsGroupName()
                    .stream()
                    .map(studentGroupRepository::findByName)
                    .collect(Collectors.toSet());

            for (Optional<StudentGroup> studentGroup : studentGroups) {
                studentGroup.ifPresent(group -> subject.getStudentGroups().add(group));
            }
        }

        subjectRepository.save(subject);

        return toDTO(subject);
    }

    @Override
    public boolean delete(SubjectDTO subjectDTO) {
        Subject subject = fromDTO(subjectDTO);
        if (subject.getId() != null) {
            subjectRepository.delete(subject);
            return true;
        }
        return false;
    }

    @Override
    public SubjectDTO toDTO(Subject object) {
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(object.getId());
        subjectDTO.setName(object.getName());
        subjectDTO.setStudentsGroupName(
                object
                        .getStudentGroups()
                        .stream()
                        .map(StudentGroup::getName)
                        .collect(Collectors.toSet())
        );
        return subjectDTO;
    }

    @Override
    public Subject fromDTO(SubjectDTO subjectDTO) {
        if (subjectDTO.getId() == null) {
            return createFromDTO(subjectDTO);
        } else {
            return subjectRepository.findById(subjectDTO.getId()).orElse(createFromDTO(subjectDTO));
        }
    }

    private Subject createFromDTO(SubjectDTO subjectDTO) {
        Subject newSubject = new Subject(subjectDTO.getName());
        newSubject
                .setStudentGroups(
                        subjectDTO
                                .getStudentsGroupName()
                                .stream()
                                .map(
                                        studentsGroupName ->
                                                studentGroupRepository
                                                        .findByName(studentsGroupName)
                                                        .orElse(null)
                                )
                                .collect(Collectors.toSet())
                );
        return newSubject;
    }
}
