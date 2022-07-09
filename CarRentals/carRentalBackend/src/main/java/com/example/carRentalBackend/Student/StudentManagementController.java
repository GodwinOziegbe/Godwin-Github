package com.example.carRentalBackend.Student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/student")
public class StudentManagementController {



    private static final List<Student> studentList= Arrays.asList(
            new Student(1, "James Bond"),
            new Student(2,"Maria Jones"),
            new Student(3,"Anna Smith")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
    public List<Student> getStudentList() {

        return studentList;

    }
    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void addNewStudent(@RequestBody Student student){
        System.out.println("new student registered:"+ student);
    }

    @DeleteMapping(path="{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void removeStudent(@PathVariable ("studentId")Integer studentId){
        System.out.println(studentId+" was deleted");
    }

    @PutMapping(path="{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable Integer studentId, @RequestBody Student student){
        System.out.println("student updated");
        System.out.println(String.format("%s %s",studentId,student));
    }

}
