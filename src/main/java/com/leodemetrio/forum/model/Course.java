package com.leodemetrio.forum.model;

import lombok.Data;

@Data
public class Course {

    private Long id;
    private String name;
    private String category;

    public Course(String name, String category) {
        this.name = name;
        this.category = category;
    }
}
