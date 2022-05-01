package com.ssj.mysqldemo.service.impl;

import com.ssj.mysqldemo.dao.StudentDao;
import com.ssj.mysqldemo.mapper.StudentMapper;
import com.ssj.mysqldemo.model.Student;
import com.ssj.mysqldemo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentDao studentDao;
    @Override
    public Integer addStudent(Student student) {
//        return studentDao.addStudent(student);
        return studentMapper.insertOne(student);
    }

    @Override
    public Integer deleteStudent(Integer id) {
        Integer result =studentDao.deleteById(id);
        return result;
    }

    @Override
    public Integer updateStudent(Student student) {
        studentDao.updateStudent(student);
        return null;
    }

    @Override
    public List<Student> queryStudent() {
        return studentMapper.listStudent();
    }

    @Override
    public Student queryStudentById(Integer id) {
        return studentDao.queryStudentById(id);
    }
    @Override
    public Student getOne(Integer id) {
        log.info("return from getOne()");
        return studentMapper.getOne(id);
    }
}
