package com.example.student_management_api.service.impl;

import com.example.student_management_api.entity.Student;
import com.example.student_management_api.exception.ResourceNotFoundException;
import com.example.student_management_api.repository.StudentRepository;
import com.example.student_management_api.service.StudentService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Student createStudent(Student student) {
        if (repo.existsByEmail(student.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        return repo.save(student);
    }

    @Override
    public Student updateStudent(Long id, Student s) {
        Student student = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
        student.setName(s.getName());
        student.setEmail(s.getEmail());
        student.setCourse(s.getCourse());
        student.setAge(s.getAge());
        return repo.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        repo.delete(student);
    }

    @Override
    public Page<Student> getAllStudents(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return repo.findAll(pageable);
    }

    @Override
    public List<Student> searchByName(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    // ✅ Add search by course
    @Override
    public List<Student> searchByCourse(String course) {
        return repo.findByCourseContainingIgnoreCase(course);
    }
}
