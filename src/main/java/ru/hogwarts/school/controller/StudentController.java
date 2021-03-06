package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Long studentId) {
        Student student = studentService.getStudent(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping()
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/age/{studentAge}")
    public ResponseEntity<List<Student>> getStudentsByAge(@PathVariable int studentAge) {
        List<Student> studentsByAge = studentService.getStudentsByAge(studentAge);
        if (studentsByAge == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentsByAge);
    }

    @GetMapping("/age/")
    public ResponseEntity<List<Student>> findByAgeBetween(@RequestParam int min, @RequestParam int max) {
        List<Student> studentsByAgeBetween = studentService.findByAgeBetween(min, max);
        if (studentsByAgeBetween == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentsByAgeBetween);
    }

    @GetMapping("/student-number")
    public Integer getStudentNumber() {
        return studentService.getStudentNumber();
    }

    @GetMapping("/students-avg-age")
    public Integer getStudentsAverageAge() {
        return studentService.getStudentsAverageAge();
    }

    @GetMapping("/five-last-students")
    public ResponseEntity<List<Student>> getFiveLastStudents() {
        List<Student> fiveLastStudents = studentService.getFiveLastStudents();
        if (fiveLastStudents.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(fiveLastStudents);
    }

    @GetMapping("names-starting-with-a")
    public List<String> getAllStudentsNamesStartingWithA() {
        return studentService.getAllStudentsNamesStartingWithA();
    }

    @GetMapping("/students-avg-age-using-stream")
    public double getStudentsAverageAgeUsingStream() {
        return studentService.getStudentsAverageAgeUsingStream();
    }

    @GetMapping("parallel-threads")
    public void getStudentsNamesInParallelThreads() {
        studentService.getStudentsNamesInParallelThreads();
    }

    @GetMapping("synchronised-threads")
    public void getStudentsNamesInSynchronisedThreads() {
        studentService.getStudentsNamesInSynchronisedThreads();
    }

}
