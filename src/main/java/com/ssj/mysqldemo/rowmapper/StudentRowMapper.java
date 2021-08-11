package com.ssj.mysqldemo.rowmapper;

import com.ssj.mysqldemo.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {

        Student student = null;
        System.out.println(resultSet.getFetchSize());
        student = new Student();
        student.setId(resultSet.getInt(1));
        student.setName(resultSet.getString(2));
        student.setAge(resultSet.getInt(3));
        student.setSex(resultSet.getString(4));

        return student;
    }
}
