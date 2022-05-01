package com.ssj.mysqldemo.mapper;

import com.ssj.mysqldemo.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    Student getOne(Integer id);
    List<Student> listStudent();
    Integer insertOne(@Param("student") Student student);
}
