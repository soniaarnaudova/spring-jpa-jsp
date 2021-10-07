package com.soar.spring.jpa.crud.controller;

import com.soar.spring.jpa.crud.entity.Student;
import com.soar.spring.jpa.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileInputStream;
import java.util.List;

@Controller
public class StudentJspController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/index")
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addStudent() {
        ModelAndView modelAndView = new ModelAndView("addStudent");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @RequestMapping(value = "/addnew", method = RequestMethod.POST)
    public ModelAndView saveStudent(@ModelAttribute Student student) {
        ModelAndView modelAndView = new ModelAndView("home");
        Student studen = studentService.saveStudent(student);
        String message = "Student was successfully added.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{studentId}", method = RequestMethod.GET)
    public ModelAndView editStudent(@PathVariable Long studentId) {
        ModelAndView modelAndView = new ModelAndView("editStudent");
        Student student = studentService.getStudent(studentId);
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @RequestMapping(value = "/update/{id}")
    public ModelAndView editingStudent(@ModelAttribute Student student, @PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("editStudent");
        Student studentEdited = studentService.updateStudent(id, student);
        String message = "Student was successfully edited.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{studentId}")
    public ModelAndView deleteStudent(@PathVariable Long studentId) {
        Student s = studentService.deleteStudentById(studentId);
        ModelAndView modelAndView = new ModelAndView("home");
        String message = "Student with id = " + studentId + " was successfully deleted.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView showListOfStudents(){
        List<Student> list = studentService.getAllStudents();
        ModelAndView modelAndView = new ModelAndView("listOfStudents");
        modelAndView.addObject("list", list);
        System.out.println("List of students:"+list.toString());
        return modelAndView;
    }

    // only reads and displays on jsp
    @RequestMapping("/displayList")
    public ModelAndView readFromExcel(@RequestParam("file") MultipartFile file) {
        ModelAndView mav = new ModelAndView();
        String message = "";
        if (file.isEmpty()) {
            mav.setViewName("home");
            mav.addObject("message", "Please select a file to upload");
            return mav;
        }

        String ext = "";
        String name = file.getOriginalFilename();
        if (name.lastIndexOf(".") != -1 && name.lastIndexOf(".") != 0) {
            ext = name.substring(name.lastIndexOf(".") + 1);
        }
        // checks for correct excel file
        if (!(ext.equalsIgnoreCase("xls") || ext.equalsIgnoreCase("xlsx"))) {
            mav.setViewName("home");
            mav.addObject("message", "Please enter a correct excel file.");
            return mav;
        }
        try {
            FileInputStream f = (FileInputStream) file.getInputStream();
            // does starting job
            List<Student> studentsIn = studentService.showFiles(f, name);
            System.out.println("STUDENTS IN:"+studentsIn.toString());
            mav.setViewName("showFromExcel");
            mav.addObject("message", message);
            mav.addObject("studentsIn", studentsIn);


        } catch (Exception e) {
            e.printStackTrace();
        }
        // get list of existing students by email search from xls file


        return mav;
    }
    
    @RequestMapping("/email")
    public ModelAndView getStudentByEmail(@RequestParam("email") String email){
        ModelAndView mv = new ModelAndView();
        Student student = null;
        if (email != null && !"".equals(email)) {
                student = studentService.findStudentByEmail(email);
                if(student != null){
                    mv.setViewName("editStudent");
                    mv.addObject("student", student);
                    mv.addObject("message", "");
                    mv.addObject("studentId",student.getStudentId());
                }else{
                    mv.setViewName("error");
                    mv.addObject("message", "No such student.");
                }
            }

        return  mv;
    }


}
