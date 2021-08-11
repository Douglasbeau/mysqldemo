package com.ssj.mysqldemo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class Student {
    Integer id;
    String name;
    Integer age;
    String sex;
}
