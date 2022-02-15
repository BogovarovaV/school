package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);
    Student getStudent(Long studentId);
    Student updateStudent(Student student);
    Student deleteStudent(Long studentId);
    List<Student> getStudentsByAge(int studentAge);
}
