package com.ssj.mysqldemo.service.impl;

import com.ssj.mysqldemo.dao.StudentDao;
import com.ssj.mysqldemo.model.Student;
import com.ssj.mysqldemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Override
    public Integer addStudent(Student student) {
        return studentDao.addStudent(student);
    }

    @Override
    public Integer deleteStudent(Integer id) {
        Integer result =studentDao.deleteById(id);
        return result;
    }

    @Override
    public Integer updateStudent(Student student) {
        return null;
    }

    @Override
    public List<Student> queryStudent() {
        return studentDao.queryStudents("select * from students;");
    }

    @Override
    public Student queryStudentById(Integer id) {
        return studentDao.queryStudentById(id);
    }
}
