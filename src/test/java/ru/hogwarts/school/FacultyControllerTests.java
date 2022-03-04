package ru.hogwarts.school;

import net.minidev.json.JSONObject;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.FacultyServiceImpl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.hogwarts.school.SchoolApplicationTestsConstants.*;

@WebMvcTest(controllers = FacultyController.class)
public class FacultyControllerTests {

    private Faculty faculty;
    private Collection<Faculty> facultyList;
    private JSONObject facultyObject;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyServiceImpl facultyServiceImpl;

    @SpyBean
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @BeforeEach
    void initDataForTest() throws JSONException {
        faculty = new Faculty();
        faculty.setId(TEST_FACULTY_ID);
        faculty.setName(TEST_FACULTY_NAME);
        faculty.setColor(TEST_FACULTY_COLOR);

        Faculty faculty1 = new Faculty();
        faculty1.setId(TEST_FACULTY_ID);
        faculty1.setName(TEST_FACULTY_NAME);
        faculty1.setColor(TEST_FACULTY_COLOR);

        Faculty faculty2 = new Faculty();
        faculty2.setId(TEST_FACULTY_ID);
        faculty2.setName(TEST_FACULTY_NAME);
        faculty2.setColor(TEST_FACULTY_COLOR);

        facultyList = List.of(
                faculty,
                faculty1,
                faculty2
        );

        facultyObject = new JSONObject();
        facultyObject.put("name", TEST_FACULTY_NAME);
        facultyObject.put("color", TEST_FACULTY_COLOR);
    }

    @Test
    void createFacultyTest() throws Exception {
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(TEST_FACULTY_ID))
                .andExpect(jsonPath("$.name").value(TEST_FACULTY_NAME))
                .andExpect(jsonPath("$.color").value(TEST_FACULTY_COLOR));
    }

    @Test
    void getFacultyByIdTest() throws Exception {
        when(facultyRepository.findById(anyLong())).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + TEST_FACULTY_ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(TEST_FACULTY_ID))
                .andExpect(jsonPath("$.name").value(TEST_FACULTY_NAME))
                .andExpect(jsonPath("$.color").value(TEST_FACULTY_COLOR));
    }

    @Test
    void updateFacultyTest() throws Exception {
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(TEST_FACULTY_ID))
                .andExpect(jsonPath("$.name").value(TEST_FACULTY_NAME))
                .andExpect(jsonPath("$.color").value(TEST_FACULTY_COLOR));
    }

    @Test
    void deleteFacultyTest() throws Exception {
        doNothing().when(facultyRepository).deleteById(anyLong());

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + TEST_FACULTY_ID))
                .andExpect(status().isOk());
    }

    @Test
    void findFacultyByNameOrColor() throws Exception {
        when(facultyRepository.findFacultiesByNameOrColorIgnoreCase(anyString(), anyString())).thenReturn((List<Faculty>) facultyList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/name-or-color/?name=" + TEST_FACULTY_NAME + "&color=" + TEST_FACULTY_COLOR)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
