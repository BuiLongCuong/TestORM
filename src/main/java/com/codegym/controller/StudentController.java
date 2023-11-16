package com.codegym.controller;

import com.codegym.model.Student;
import com.codegym.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @GetMapping("")
    public String home(Model model) {
        List<Student> studentList = studentService.findAll();
        model.addAttribute("students", studentList);
        return "/home";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("student", new Student());
        return "/create";
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.findStudentById(id));
        return "/update";
    }

    @PostMapping("/update")
    public String update(Student student) {
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Student student = studentService.findStudentById(id);
        studentService.remove(student.getId());
        redirectAttributes.addFlashAttribute("success", "-- Đã xóa học sinh có id " + student.getId() +" thành công! --");
        return "redirect:/students";
    }

    @PostMapping("/save")
    public String save(Student student) {
        studentService.save(student);
        return "redirect:/students";
    }
    @GetMapping("/search")
    public ModelAndView showSearch(@RequestParam(name = "q") String q) {
        List<Student> students;
        if (q.isEmpty()) students = studentService.findAll();
        else students = studentService.findStudentByName(q);
        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.addObject("students", students);
        return modelAndView;
    }
}
