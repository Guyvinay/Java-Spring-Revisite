package com.pros.repository;

import com.pros.modal.Student;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    public Optional<Student> findByStudentName(String name);
    @Query("SELECT s FROM Student s WHERE LOWER(s.studentName) LIKE LOWER(CONCAT('%', :name, '%'))")
    public List<Student> findStudentByNamePattern(@Param("name") String name);

    @QueryHints(@QueryHint(
            name = org.hibernate.annotations.QueryHints.CACHEABLE,
            value = "true"
    ))
    @Query("SELECT s FROM Student s")
    List<Student> findAllStudents();
}
