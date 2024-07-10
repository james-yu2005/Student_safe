package com.james.__hour_tutorial.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    // which service we want to test?
    @InjectMocks
    private StudentService studentService;

    // declare dependencies
    @Mock
    private StudentRepository repository;
    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_save_a_student() {
        // Given
        StudentDto dto = new StudentDto(
                "James",
                "Yu",
                "@gmail.com",
                1
        );
        Student student = new Student(
                "James",
                "Yu",
                "@gmail",
                19
        );
        Student savedStudent = new Student(
                "James",
                "Yu",
                "@gmail",
                19
        );

        // Mock calls
        Mockito.when(studentMapper.toStudent(dto))
                .thenReturn(student);
        Mockito.when(repository.save(student))
                .thenReturn(savedStudent);
        repository.save(student);
        Mockito.when(studentMapper.toStudentResponseDto(savedStudent))
                .thenReturn(new StudentResponseDto(
                        "James",
                        "Yu",
                        "@gmail.com"
                ));
        // When
        StudentResponseDto responseDto = studentService.saveStudent(dto);

        // Then
        assertEquals(dto.firstname(), responseDto.firstname());
        assertEquals(dto.lastname(), responseDto.lastname());
        assertEquals(dto.email(), responseDto.email());

        Mockito.verify(studentMapper, Mockito.times(1)).toStudent(dto);
        Mockito.verify(repository, Mockito.times(2)).save(student);
        Mockito.verify(studentMapper, Mockito.times(1)).toStudentResponseDto(savedStudent);
    }

    @Test
    public void should_return_all_students() {
        // Given
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "John",
                "Yu",
                "gmail",
                20
        ));

        // Mock the calls
        Mockito.when(repository.findAll()).thenReturn(students);
        Mockito.when(studentMapper.toStudentResponseDto(Mockito.any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "John",
                        "Jo",
                        "gmail2"
                ));

        // When
        List<StudentResponseDto> responseDtos = studentService.findAllStudent();

        assertEquals(students.size(), responseDtos.size());
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    public void should_return_student_by_id() {

        // Given
        Integer studentId = 1;
        Student student = new Student(
                "James",
                "Yu",
                "@gmail",
                19
        );
        // Mock the calls
        Mockito.when(repository.findById(studentId))
                .thenReturn(Optional.of(student));
        Mockito.when(studentMapper.toStudentResponseDto(Mockito.any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "James",
                        "Yu",
                        "@gmail"
                ));
        // When
        StudentResponseDto dto = studentService.findStudentById(studentId);
        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());
    }

    @Test
    public void should_find_students_by_name() {

        // Given
        String name = "james";
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "John",
                "Yu",
                "gmail",
                20
        ));

        // When
        Mockito.when(repository.findAllByfirstnameContaining(name))
                .thenReturn(students);
        Mockito.when(studentMapper.toStudentResponseDto(Mockito.any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "James",
                        "Yu",
                        "@gmail"
                ));
        List <StudentResponseDto> dtos = studentService.findStudentsByFirstName(name);
        assertEquals(students.size(), dtos.size());
        Mockito.verify(repository, Mockito.times(1))
                .findAllByfirstnameContaining(name);
    }

    @Test
    public void should_delete_by_id() {
        // Given
        Integer id = 1;

        studentService.delete(id);

        Mockito.verify(repository,Mockito.times(1)).deleteById(id);
    }
}