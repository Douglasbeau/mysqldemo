package com.ssj.mysqldemo.service;

import com.ssj.mysqldemo.model.Student;

import java.util.List;

public interface StudentService {
    Integer addStudent(Student student);
    Integer deleteStudent(Integer id);
    Integer updateStudent(Student student);
    List<Student> queryStudent();
    Student getOne(Integer id);

}
