package com.leodemetrio.forum.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Answer {

    private Long id;
    private String message;
    private Topic topic;
    private LocalDateTime dataCreation = LocalDateTime.now();
    private User author;
    private Boolean solution = false;

}
