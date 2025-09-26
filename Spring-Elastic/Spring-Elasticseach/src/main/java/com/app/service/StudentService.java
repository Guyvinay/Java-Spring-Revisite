package com.app.service;

import com.app.modal.Student;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(String id);

    Student getStudentByName(String name);

    List<Student> getStudentsByNamePattern(String name);

    Student updateStudentById(String id, Student student);

    String deleteStudentById(String id);

}
