package com.app.service.impl;

import com.app.modal.Student;
import com.app.repository.StudentRepository;
import com.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        return null;
    }

    @Override
    public List<Student> getAllStudents() {
        return null;
    }

    @Override
    public Student getStudentById(String id) {
        return null;
    }

    @Override
    public Student getStudentByName(String name) {
        return null;
    }

    @Override
    public List<Student> getStudentsByNamePattern(String name) {
        return null;
    }

    @Override
    public Student updateStudentById(String id, Student student) {
        return null;
    }

    @Override
    public String deleteStudentById(String id) {
        return null;
    }
}
