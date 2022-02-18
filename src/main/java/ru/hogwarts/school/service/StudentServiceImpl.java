package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    private final Map<Long, Student> students = new HashMap<>();
    private Long id = 0L;

    @Override
    public Student createStudent(Student student) {
        student.setId(++id);
        students.put(id, student);
        return student;
    }

    @Override
    public Student getStudent(Long studentId) {
        return students.get(studentId);
    }

    @Override
    public Student updateStudent(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Student deleteStudent(Long studentId) {
        return students.remove(studentId);
    }

    @Override
    public List<Student> getStudentsByAge(int studentAge) {
        Collection<Student> allStudents = students.values();
        return allStudents.stream()
                .filter(student -> student.getAge() == studentAge)
                .collect(Collectors.toList());
    }
}
