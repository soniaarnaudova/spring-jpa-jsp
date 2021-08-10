package com.soar.spring.jpa.crud.controller;

import com.soar.spring.jpa.crud.entity.Student;
import com.soar.spring.jpa.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/student")
@ResponseBody
public class StudentController {

        @Autowired
        private StudentService studentService;

        @RequestMapping(value = "/save", method = RequestMethod.POST)
        @ResponseBody
        public Student saveStudent(@RequestBody Student student) {

            return studentService.saveStudent(student);
        }

        @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
        @ResponseBody
        public Student updateStudent(@RequestBody Student student, @PathVariable("id") Long id) {

            return studentService.updateStudent(id, student);
        }

        @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
        @ResponseBody
        public Student deleteStudent(@RequestBody Student student) {

            return studentService.deleteStudent(student);
        }


        @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
        @ResponseBody
        public Student getStudent(@PathVariable Long id) {

            return studentService.getStudent(id);
        }


    }

