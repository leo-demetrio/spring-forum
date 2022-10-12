package com.leodemetrio.forum.dto;

import com.leodemetrio.forum.model.Answer;
import com.leodemetrio.forum.model.StatusTopic;
import com.leodemetrio.forum.model.Topic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TopicDetailsDto {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime dataCreation = LocalDateTime.now();
    private String nameAuthor;
    private StatusTopic statusTopic;
    private List<AnswerDto> answerList;

    public TopicDetailsDto(Topic t){
        this.id = t.getId();
        this.title = t.getTitle();
        this.message = t.getMessage();
        this.dataCreation = t.getDataCreation();
        this.nameAuthor = t.getAuthor().getName();
        this.statusTopic = t.getStatus();
        this.answerList = new ArrayList<>();
        this.answerList.addAll(t.getAnswers().stream().map(AnswerDto::new).collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDataCreation() {
        return dataCreation;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public StatusTopic getStatusTopic() {
        return statusTopic;
    }

    public List<AnswerDto> getAnswerList() {
        return answerList;
    }
}
