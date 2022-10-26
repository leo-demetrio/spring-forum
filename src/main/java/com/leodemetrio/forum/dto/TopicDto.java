package com.leodemetrio.forum.dto;

import com.leodemetrio.forum.model.Topic;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;



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
    public static Page<TopicDto> convert(Page<Topic> topics){
        return topics.map(TopicDto::new);
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
