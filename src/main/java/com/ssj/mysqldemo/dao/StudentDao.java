package com.ssj.mysqldemo.dao;

import com.ssj.mysqldemo.model.Student;
import com.ssj.mysqldemo.rowmapper.StudentRowMapper;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class StudentDao {
    @Autowired
    @Qualifier("dbcp2JdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("hikariJdbcTemplate")
    private JdbcTemplate h;

    public List<Student> queryStudents(String sql) {

//        System.out.printf("%s%n", third.getDataSource());
        return h.query(sql, new StudentRowMapper());
    }

    public Student queryStudentById(Integer id) {
        BasicDataSource dataSource = (BasicDataSource)jdbcTemplate.getDataSource();
        System.out.println(dataSource);
        return jdbcTemplate.queryForObject("select * from students where id = " + id, new StudentRowMapper());
    }

    public Integer addStudent(Student student) {
        return jdbcTemplate.update("INSERT INTO ssj.students (name, age, sex) VALUES (?,?,?)",
            student.getName(), student.getAge(), student.getSex());
    }

    public Integer deleteById(Integer id) {
        Integer a = jdbcTemplate.update("delete from students where id =?", id);
        return a;
    }

    public Integer updateStudent(Student student) {
        return jdbcTemplate.update("update students set name = ?, age =?, sex = ? where id = ?",
                student.getName(), student.getAge(), student.getSex(), student.getId());
    }
}
