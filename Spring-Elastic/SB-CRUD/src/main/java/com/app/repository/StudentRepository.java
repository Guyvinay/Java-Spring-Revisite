package com.app.repository;

import com.app.modal.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.Style;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    public Optional<Student> findByStudentName(String name);
    @Query("SELECT s FROM Student s WHERE LOWER(s.studentName) LIKE LOWER(CONCAT('%', :name, '%'))")
    public List<Student> findStudentByNamePattern(@Param("name") String name);
}
