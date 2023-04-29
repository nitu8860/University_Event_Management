package com.example.University_Event_Management.service;

import com.example.University_Event_Management.exception.ResourceNotFoundException;
import com.example.University_Event_Management.model.Department;
import com.example.University_Event_Management.model.Student;
import com.example.University_Event_Management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudentDepartment(int studentId, Department department) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + studentId));
        student.setDepartment(department);
        return studentRepository.save(student);
    }

    public void deleteStudent(int studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + studentId));
        studentRepository.delete(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + studentId));
    }
}