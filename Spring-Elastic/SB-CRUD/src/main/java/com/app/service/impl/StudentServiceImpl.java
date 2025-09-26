package com.app.service.impl;

import com.app.exception.StudentNotFoundException;
import com.app.modal.Student;
import com.app.modal.Tech;
import com.app.repository.StudentRepository;
import com.app.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty())
            throw new StudentNotFoundException("No Students Found!!!");
        return students;
    }

    @Override
    public Student getStudentById(String id) {
        return studentRepository.findById(id).orElseThrow(
                () -> new StudentNotFoundException("Student with id: " + id + ", not found")
        );
    }

    @Override
    public Student getStudentByName(String name) {

        return studentRepository.findByStudentName(name).orElseThrow(
                () -> new StudentNotFoundException("Student with name: " + name + ", not found")
        );
    }

    @Override
    public List<Student> getStudentsByNamePattern(String name) {
        List<Student> students = studentRepository.findStudentByNamePattern(name);

        if (students.isEmpty())
            throw new StudentNotFoundException("No Students Found!!!");
        return students;
    }

    @Override
    public Student updateStudentById(String id, Student student) {
        Student retrievedStudent = studentRepository.findById(id).orElseThrow(
                () -> new StudentNotFoundException("Student with id: " + id + ", not found")
        );
        String name = student.getStudentName();
        String email = student.getEmail();
        String profilePic = student.getProfilePic();
        List<Tech> techs = student.getTechStacks();
        if (!StringUtils.isBlank(name)) {
            retrievedStudent.setStudentName(student.getStudentName());
        }
        if (!StringUtils.isBlank(email)) {
            if (!email.isEmpty()) retrievedStudent.setEmail(email);
        }
        if (!StringUtils.isBlank(profilePic)) {
            retrievedStudent.setProfilePic(profilePic);
        }
        if (!CollectionUtils.isEmpty(techs)) {
            retrievedStudent.setTechStacks(techs);
        }

        return studentRepository.save(retrievedStudent);
    }

    @Override
    public String deleteStudentById(String id) {

        Student retrievedStudent = studentRepository.findById(id).orElseThrow(
                () -> new StudentNotFoundException("Student with id: " + id + ", not found")
        );
        studentRepository.delete(retrievedStudent);
        return "Student Deleted";
    }
}
