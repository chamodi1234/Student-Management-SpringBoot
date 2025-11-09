package com.example.student_management_api.repository;

import com.example.student_management_api.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Search by name (already present)
    List<Student> findByNameContainingIgnoreCase(String name);

    // Search by course (new)
    List<Student> findByCourseContainingIgnoreCase(String course);

    // Check if email exists
    boolean existsByEmail(String email);
}
