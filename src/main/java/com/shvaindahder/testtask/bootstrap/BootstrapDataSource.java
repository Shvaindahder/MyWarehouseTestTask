package com.shvaindahder.testtask.bootstrap;

import com.shvaindahder.testtask.entity.Student;
import com.shvaindahder.testtask.entity.StudentGroup;
import com.shvaindahder.testtask.entity.Subject;
import com.shvaindahder.testtask.repository.MarksRepository;
import com.shvaindahder.testtask.repository.StudentRepository;
import com.shvaindahder.testtask.repository.StudentGroupRepository;
import com.shvaindahder.testtask.repository.SubjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class BootstrapDataSource implements CommandLineRunner {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final MarksRepository marksRepository;

    public BootstrapDataSource(
            StudentRepository studentRepository,
            SubjectRepository subjectRepository,
            StudentGroupRepository studentGroupRepository,
            MarksRepository marksRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.studentGroupRepository = studentGroupRepository;
        this.marksRepository = marksRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        StudentGroup bsbo0917 = new StudentGroup("BSBO-09-17");
        StudentGroup bsbo1017 = new StudentGroup("BSBO-10-17");

        Student student1 = new Student("Simonyan");
        Student student2 = new Student("Brusnikova");
        Student student3 = new Student("Shekurova");
        Student student4 = new Student("Smykov");

        Subject matan = new Subject("Math Analysis");
        Subject linal = new Subject("Linear Algebra");
        Subject subject3 = new Subject("subject3");
        Subject subject4 = new Subject("subject4");
        Subject subject5 = new Subject("subject5");

        for (Subject subject : List.of(matan, linal, subject3)) {
            subject.getStudentGroups().add(bsbo0917);
        }

        for (Subject subject : List.of(matan, linal, subject4, subject5)) {
            subject.getStudentGroups().add(bsbo1017);
        }

        for (Student student : List.of(student1, student2)) {
            student.setGroup(bsbo0917);
        }

        for (Student student : List.of(student3, student4)) {
            student.setGroup(bsbo1017);
        }

        studentGroupRepository.saveAll(List.of(bsbo1017, bsbo0917));
        studentRepository.saveAll(List.of(student1, student2, student3, student4));
        subjectRepository.saveAll(List.of(matan, linal, subject3, subject4, subject5));
    }
}
