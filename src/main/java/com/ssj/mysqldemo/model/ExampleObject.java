package com.ssj.mysqldemo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Setter
@Getter
public class ExampleObject {
    private List someList;
    private Map someMap;
    private Set someSet;

}
