package com.leodemetrio.forum.dto;

import com.leodemetrio.forum.model.Topic;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class TopicDto {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime dataCreation = LocalDateTime.now();

    public TopicDto(Topic topic){
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.dataCreation = topic.getDataCreation();
    }
    public static List<TopicDto> convert(List<Topic> topic){
        return topic.stream().map(TopicDto::new).collect(Collectors.toList());
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
}
