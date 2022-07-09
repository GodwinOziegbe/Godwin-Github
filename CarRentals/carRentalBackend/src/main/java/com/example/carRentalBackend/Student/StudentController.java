package com.example.carRentalBackend.Student;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private static final List<Student> studentList= Arrays.asList(
            new Student(1, "James Bond"),
            new Student(2,"Maria Jones"),
            new Student(3,"Anna Smith")
    );
    @GetMapping("{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId) {
     return studentList.stream()
        .filter(student -> studentId.equals(student.getStudentId()))
        .findFirst()
        .orElseThrow(()-> new IllegalStateException("Student"+studentId+"does not exist"));
    }

    }
