package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ru.hogwarts.school.SchoolApplicationTestsConstants.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolApplicationTests {

    private Student student;
    private long studentId;

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void initTestStudent() {
        student = new Student();
        student.setId(TEST_ID_1);
        student.setName(TEST_NAME_1);
        student.setAge(TEST_AGE_1);
    }

    @Test
    void contextLoads() {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testCreateStudent() {
        studentId = restTemplate.postForObject("http://localhost:" + port + "/student", student, Student.class).getId();
        student.setId(studentId);
                Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, Student.class))
                .isEqualTo(student);
    }
    @Test
    public void testUpdateStudent() {
        student.setId(10L);
        student.setName("Hermione Granger");
        restTemplate.put("http://localhost:" + port + "/student", student);
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/10", Student.class))
                .isEqualTo(student);
    }

    @Test
    public void testDeleteStudentById() {
        restTemplate.delete("http://localhost:" + port + "/student/TEST_ID_1");
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", Student.class))
                .isNotIn(student);
    }


    @Test
    public void testGetStudentById() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/TEST_ID_1", Student.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentByAge() {
        student.setAge(TEST_AGE_1);
        restTemplate.put("http://localhost:" + port + "/student", student);
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/age/TEST_AGE_1", Student.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentByAgeBetween() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/age/?min=TEST_AGE_2&max=TEST_AGE_3", Student.class))
                .isNotNull();
    }



}
