package com.leodemetrio.forum.model;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Data
public class Topic {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime dataCreation = LocalDateTime.now();
    private StatusTopic status = StatusTopic.NOT_RESPONSE;
    private User author;
    private Course course;
    private List<Answer> answers = new ArrayList<>();

    public Topic(String title, String message, Course course) {
        this.title = title;
        this.message = message;
        this.course = course;
    }
}
