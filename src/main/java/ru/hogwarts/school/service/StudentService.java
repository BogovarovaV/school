package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);
    Student getStudent(Long studentId);
    Student updateStudent(Student student);
    void deleteStudent(Long studentId);
    List<Student> getStudentsByAge(int studentAge);
    List<Student> findByAgeBetween(int min, int max);

    int getStudentNumber();
    int getStudentsAverageAge();
    List<Student> getFiveLastStudents();
}
