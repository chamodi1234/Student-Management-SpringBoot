package com.example.student_management_api.service;

import com.example.student_management_api.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    Student updateStudent(Long id, Student student);

    Student getStudentById(Long id);

    void deleteStudent(Long id);

    Page<Student> getAllStudents(int page, int size, String sortBy, String sortDir);

    List<Student> searchByName(String name);

    // ✅ Add this method to support search by course
    List<Student> searchByCourse(String course);
}
