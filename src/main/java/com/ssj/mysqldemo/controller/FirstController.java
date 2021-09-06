package com.ssj.mysqldemo.controller;

import com.ssj.mysqldemo.model.Student;
import com.ssj.mysqldemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class FirstController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap modelMap){
        List<Student> students = studentService.queryStudent();
        modelMap.addAttribute("sList", students);
        return "list";
    }

    @GetMapping( "/{id}")
    public Student fetch(@PathVariable Integer id) {
        return studentService.queryStudentById(id);
    }

    @PostMapping("/add")
    public String addOrUpdateStudent(@ModelAttribute Student student, Model map) {
        Integer result = studentService.addStudent(student);
        map.addAttribute("success", result == 1);
        return "index";
    }

    @GetMapping("/deleteById")
    public String deleteById(@RequestParam("id") Integer id){
        studentService.deleteStudent(id);
        return "redirect:list";
    }

    @PutMapping("/update")
    public void update(@ModelAttribute Student student) {
        studentService.updateStudent(student);
    }

}
