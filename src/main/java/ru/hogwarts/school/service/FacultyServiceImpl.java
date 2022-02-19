package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService{

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFaculty(Long facultyId) {
        return facultyRepository.findById(facultyId).get();
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(Long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

    @Override
    public List<Faculty> getFacultiesByColor(String facultyColor) {
        Collection<Faculty> allFaculties = facultyRepository.findAll();
        return allFaculties.stream()
                .filter(faculty -> Objects.equals(faculty.getColor(), facultyColor))
                .collect(Collectors.toList());
    }
}
