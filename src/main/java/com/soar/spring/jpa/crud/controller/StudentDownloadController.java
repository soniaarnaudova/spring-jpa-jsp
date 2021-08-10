package com.soar.spring.jpa.crud.controller;

import com.soar.spring.jpa.crud.entity.Student;
import com.soar.spring.jpa.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class StudentDownloadController {

    @Autowired
    StudentService studentService;

    @GetMapping(value = "/download/students")
    public ResponseEntity<InputStreamResource> excelAllStudentsReport() throws IOException {

        List<Student> students = studentService.getAllStudents();

        ByteArrayInputStream in = studentService.getStudentsToExcel(students);
        // return IOUtils.toByteArray(in);


        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=studets.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

}
