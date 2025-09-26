package com.app.controller;

import com.app.modal.Student;
import com.app.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student) {

        return new ResponseEntity<>(
                studentService.saveStudent(student),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping()
    public ResponseEntity<List<Student>> getAllStudents() {

        return new ResponseEntity<>(
                studentService.getAllStudents(),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping(value = "id/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") String id) {
        return new ResponseEntity<>(
                studentService.getStudentById(id),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(
                studentService.getStudentByName(name),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping(value = "/namePattern/{name}")
    public ResponseEntity<List<Student>> getStudentsByNamePattern(@PathVariable("name") String name) {
        return new ResponseEntity<>(
                studentService.getStudentsByNamePattern(name),
                HttpStatus.ACCEPTED
        );
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Student> updateStudentById(@PathVariable("id") String id, @RequestBody Student student) {
        return new ResponseEntity<>(
                studentService.updateStudentById(id, student),
                HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable("id") String id) {
        return new ResponseEntity<>(
                studentService.deleteStudentById(id),
                HttpStatus.ACCEPTED
        );
    }

}
