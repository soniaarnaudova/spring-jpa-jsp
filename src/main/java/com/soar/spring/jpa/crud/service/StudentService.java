package com.soar.spring.jpa.crud.service;

import com.soar.spring.jpa.crud.entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
public interface StudentService {

    Student saveStudent(Student student);

    Student deleteStudent(Student student);

    @Modifying
    Student updateStudent(Long id, Student student);

    Student getStudent(Long id);

    Student deleteStudentById(Long studentId);

    List<Student> getAllStudents();

    ByteArrayInputStream getStudentsToExcel(List<Student> students) throws IOException;

    List<Student> showFiles(FileInputStream f, String name);

    Student findStudentByEmail(String email);
}
