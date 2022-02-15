package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService{

    private Map<Long, Student> students = new HashMap<>();
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
        List <Student> studentsByAge = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            Student currentStudent = students.get(i);
            if (currentStudent.getAge() == studentAge) {
                studentsByAge.add(currentStudent);
            }
        }
        return studentsByAge;
    }
}
