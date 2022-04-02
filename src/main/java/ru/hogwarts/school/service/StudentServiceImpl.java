package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    int count = 1;

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

    @Override
    public List<String> getAllStudentsNamesStartingWithA() {
        logger.info("Was invoked method for get all students' names starting with A");
        List<String> namesStartingWithA = studentRepository.findAll().stream()
                .parallel()
                .map(Student::getName)
                .filter(n -> n.startsWith("A"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
        if (namesStartingWithA.isEmpty()) {
            logger.warn("There is no students' names starting with A");
        }
        return namesStartingWithA;
    }

    @Override
    public double getStudentsAverageAgeUsingStream() {
        logger.info("Was invoked method for get five last students using Stream");
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0);
    }

    @Override
    public void getStudentsNamesInParallelThreads() {
        logger.info("Was invoked method for get students' names in parallel threads");
        System.out.println("1 - " + studentRepository.getById(1L).getName());
        System.out.println("2 - " + studentRepository.getById(13L).getName());

        new Thread(() -> {
            System.out.println("3 - " + studentRepository.getById(14L).getName());
            System.out.println("4 - " + studentRepository.getById(18L).getName());

        }).start();

        new Thread(() -> {
            System.out.println("5 - " + studentRepository.getById(19L).getName());
            System.out.println("6 - " + studentRepository.getById(20L).getName());

        }).start();
    }

    @Override
    public void getStudentsNamesInSynchronisedThreads() {
        logger.info("Was invoked method for get students' names in synchronised threads");
        getNamesById(1L);
        getNamesById(13L);

        new Thread(() -> {
            getNamesById(14L);
            getNamesById(18L);

        }).start();

        new Thread(() -> {
            getNamesById(19L);
            getNamesById(20L);

        }).start();
    }

    private synchronized void getNamesById(Long id) {
        System.out.println(count + " - " + studentRepository.findById(id).get().getName());
        count ++;
    }
}
