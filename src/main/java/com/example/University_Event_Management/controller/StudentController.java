package com.example.University_Event_Management.controller;

import com.example.University_Event_Management.exception.ResourceNotFoundException;
import com.example.University_Event_Management.model.Department;
import com.example.University_Event_Management.model.Student;
import com.example.University_Event_Management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("")
    public Student addStudent(@RequestBody @Validated Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudentDepartment(
            @PathVariable int studentId,
            @RequestParam Department department) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + studentId));
        student.setDepartment(department);
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + studentId));
        studentRepository.delete(student);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable int studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + studentId));
    }
}
