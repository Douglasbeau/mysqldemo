package com.ssj.mysqldemo.service.impl;

import com.ssj.mysqldemo.mapper.StudentMapper;
import com.ssj.mysqldemo.model.Student;
import com.ssj.mysqldemo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Integer addStudent(Student student) {
        log.info("Adding new student to table");
        return studentMapper.insertOne(student);
    }

    @CacheEvict(cacheNames = "student", key = "#id")
    @Override
    public Integer deleteStudent(Integer id) {
        Integer result = studentMapper.deleteById(id);
        log.info("deleted {} student", result);
        return result;
    }

    @CacheEvict(cacheNames = "student", key = "#student.id")
    @Override
    public Integer updateStudent(Student student) {
        Integer result = studentMapper.updateStudent(student);
        log.info("updated {} student", result);
        return result;
    }

    @Override
    public List<Student> queryStudent() {
        return studentMapper.listStudent();
    }

    @Cacheable(cacheNames = "student", key = "#id")
    @Override
    public Student getOne(Integer id) {
        log.info("return from getOne()");
        return studentMapper.getOne(id);
    }
}
