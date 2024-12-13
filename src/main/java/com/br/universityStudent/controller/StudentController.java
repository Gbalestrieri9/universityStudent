package com.br.universityStudent.controller;

import com.br.universityStudent.model.Student;
import com.br.universityStudent.config.SessionStudent;
import com.br.universityStudent.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SessionStudent sessionStudent;

    @GetMapping("/students/new")
    public String showStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "student";
    }

    @PostMapping("/students")
    public String saveStudentTemp(@ModelAttribute Student student) {
        student = studentService.processStudentAddress(student);
        sessionStudent.setStudent(student);
        return "redirect:/students/confirm";
    }

    @GetMapping("/students/confirm")
    public String confirmStudent(Model model) {
        Student student = sessionStudent.getStudent();
        if (student == null) {
            return "redirect:/students/new";
        }
        model.addAttribute("student", student);
        return "confirm-student";
    }

    @PostMapping("/students/confirm")
    public String confirmStudentRegistration() {
        Student student = sessionStudent.getStudent();
        if (student == null) {
            return "redirect:/students/new";
        }
        studentService.saveStudent(student);
        sessionStudent.setStudent(null);
        return "redirect:/students/success";
    }

    @PostMapping("/students/cancel")
    public String cancelStudentRegistration() {
        sessionStudent.setStudent(null);
        return "redirect:/students/new";
    }

    @GetMapping("/students/edit")
    public String editStudentForm(Model model) {
        Student student = sessionStudent.getStudent();
        if (student == null) {
            return "redirect:/students/new";
        }
        model.addAttribute("student", student);
        return "student";
    }

    @GetMapping("/students/success")
    public String registrationSuccess() {
        return "success";
    }
}
