package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService{

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    @Override
    public Student getStudent(Long studentId) {
        logger.info("Was invoked method for get student");
        return studentRepository.findById(studentId).get();
    }

    @Override
    public Student updateStudent(Student student) {
        logger.info("Was invoked method for update student");
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long studentId) {
        logger.info("Was invoked method for delete student");
        studentRepository.deleteById(studentId);
    }

    @Override
    public List<Student> getStudentsByAge(int studentAge) {
        logger.info("Was invoked method for get students by age");
        return studentRepository.findStudentByAge(studentAge);
    }

    @Override
    public List<Student> findByAgeBetween(int min, int max) {
        logger.info("Was invoked method for find students by age between");
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public int getStudentNumber() {
        logger.info("Was invoked method for get student number");
        return studentRepository.getStudentNumber();
    }

    @Override
    public int getStudentsAverageAge() {
        logger.info("Was invoked method for get students average age");
        return studentRepository.getStudentsAverageAge();
    }

    @Override
    public List<Student> getFiveLastStudents() {
        logger.info("Was invoked method for get five last students");
        return studentRepository.getFiveLastStudents();
    }
}
