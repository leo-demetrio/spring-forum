package com.leodemetrio.forum.dto;

import com.leodemetrio.forum.model.Course;
import com.leodemetrio.forum.model.Topic;
import com.leodemetrio.forum.repository.CourseRepository;
import com.leodemetrio.forum.repository.TopicRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


public class TopicRequestPutDto {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String title;

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String message;

    public TopicRequestPutDto(){}

    public TopicRequestPutDto(Topic topic){
        this.title = topic.getTitle();
        this.message = topic.getMessage();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public Topic update(Long id, TopicRepository topicRepository) {
        Topic topic = topicRepository.findById(id).get();
        topic.setTitle(this.title);
        topic.setMessage(this.message);

        return topic;
    }

}
