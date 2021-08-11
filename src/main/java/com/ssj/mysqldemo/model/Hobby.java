package com.ssj.mysqldemo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class Hobby {
    Integer id;
    String hobby;
}
