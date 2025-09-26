package com.app.repository;

import com.app.controller.StudentController;
import com.app.modal.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface StudentRepository extends ElasticsearchRepository<Student, String> {
}
