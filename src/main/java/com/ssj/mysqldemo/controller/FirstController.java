package com.ssj.mysqldemo.controller;

import com.ssj.mysqldemo.model.Student;
import com.ssj.mysqldemo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
@Slf4j
public class FirstController {
    private final StudentService studentService;

    public FirstController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap modelMap){
        List<Student> students = studentService.queryStudent();
        modelMap.addAttribute("sList", students);
        return "list";
    }

    @ResponseBody
    @GetMapping( "/{id}")
    public Student fetch(@PathVariable Integer id) {
//        return studentService.queryStudentById(id);
        return studentService.getOne(id);
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

//    @ResponseBody
//    @PostMapping("/upload")
//    public String uploadFiles(@RequestPart("file") MultipartFile mf, HttpServletResponse response)
//            throws FileNotFoundException, IOException {
//        log.info("file name: {}, file type: {}", mf.getOriginalFilename(), mf.getContentType());
//        Streams.copy(mf.getInputStream(),
//                new BufferedOutputStream(new FileOutputStream("/Users/shengjiesong/Documents/" +
//                        mf.getOriginalFilename())),
//                true);
//        response.setStatus(HttpStatus.SC_ACCEPTED);
//        return "file size: " + mf.getSize();
//    }

}
