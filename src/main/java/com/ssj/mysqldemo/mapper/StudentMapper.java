package com.ssj.mysqldemo.mapper;

import com.ssj.mysqldemo.model.Student;
import com.ssj.mysqldemo.model.StudentDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    Student getOne(Integer id);
    Student getOneByName (String name);
    List<Student> listStudent();
    Integer insertOne(@Param("student") Student student);
    Integer updateStudent(Student student);

    Integer deleteById(Integer id);

    StudentDO selectStudentRoleById(Integer id);
    StudentDO selectStudentRoleByName(String username);
}
