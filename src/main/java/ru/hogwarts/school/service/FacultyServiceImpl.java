package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

@Service
public class FacultyServiceImpl implements FacultyService{

    private static final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFaculty(Long facultyId) {
        logger.info("Was invoked method for get faculty");
        return facultyRepository.findById(facultyId).get();
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Was invoked method for update faculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(Long facultyId) {
        logger.info("Was invoked method for delete faculty");
        facultyRepository.deleteById(facultyId);
    }

    @Override
    public List<Faculty> getFacultiesByNameOrColor(String facultyName, String facultyColor) {
        logger.info("Was invoked method for get faculties by name or color");
        return facultyRepository.findFacultiesByNameOrColorIgnoreCase(facultyName, facultyColor);
    }

    @Override
    public String getFacultyWithTheLongestName() {
        logger.info("Was invoked method for get faculty with the longest name");
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElse(null);
    }

}
