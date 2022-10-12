package com.leodemetrio.forum.dto;

import com.leodemetrio.forum.model.Answer;

import java.time.LocalDateTime;

public class AnswerDto {

    private Long id;
    private String message;
    private LocalDateTime dateCreation;
    private String nameAuthor;

    public AnswerDto(Answer answer){
        this.id = answer.getId();
        this.message = answer.getMessage();
        this.dateCreation = answer.getDataCreation();
        this.nameAuthor = answer.getAuthor().getName();
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }
}
