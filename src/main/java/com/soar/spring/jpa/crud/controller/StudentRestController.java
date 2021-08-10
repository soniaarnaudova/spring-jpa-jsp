package com.soar.spring.jpa.crud.controller;

import com.soar.spring.jpa.crud.entity.Student;
import com.soar.spring.jpa.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/app")
    public class StudentRestController {

        @Autowired
        private StudentService studentService;

        @RequestMapping(value = "/student/save", method = RequestMethod.POST)
        @ResponseBody
        public Student saveStudent(@RequestBody Student student) {

            return studentService.saveStudent(student);
        }

        @RequestMapping(value = "/student/update/{id}", method = RequestMethod.PUT)
        @ResponseBody
        public Student updateStudent(@RequestBody Student student, @PathVariable("id") Long id) {

            return studentService.updateStudent(id, student);
        }

        @RequestMapping(value = "/student/delete", method = RequestMethod.DELETE)
        @ResponseBody
        public Student deleteStudent(@RequestBody Student student) {

            return studentService.deleteStudent(student);
        }


        @RequestMapping(value = "/student/find/{id}", method = RequestMethod.GET)
        @ResponseBody
        public Student getStudent(@PathVariable Long id) {

            return studentService.getStudent(id);
        }


}
