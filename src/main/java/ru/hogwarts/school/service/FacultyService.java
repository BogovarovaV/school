package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;

import java.util.List;

public interface FacultyService {

    Faculty createFaculty(Faculty faculty);
    Faculty getFaculty(Long facultyId);
    Faculty updateFaculty(Faculty faculty);
    void deleteFaculty(Long facultyId);
    List<Faculty> getFacultiesByNameOrColor(String facultyName, String facultyColor);

    String getFacultyWithTheLongestName();
}
