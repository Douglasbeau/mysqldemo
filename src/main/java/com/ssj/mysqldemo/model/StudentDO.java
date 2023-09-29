package com.ssj.mysqldemo.model;

import lombok.Data;

import java.util.List;

@Data
public class StudentDO {
    private String name;
    private List<Role> roles;
}
