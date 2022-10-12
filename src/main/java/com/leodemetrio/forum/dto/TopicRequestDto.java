package com.leodemetrio.forum.dto;

import com.leodemetrio.forum.model.Course;
import com.leodemetrio.forum.model.Topic;
import com.leodemetrio.forum.repository.CourseRepository;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


public class TopicRequestDto {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String title;

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String message;

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String nameCourse;

    public TopicRequestDto(){}

    public TopicRequestDto(Topic topic){
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.nameCourse = topic.getCourse().getName();

    }
    public static List<TopicRequestDto> convert(List<Topic> topic){
        return topic.stream().map(TopicRequestDto::new).collect(Collectors.toList());
    }
    public Topic convertToTopic(CourseRepository courseRepository) {
        Course course = courseRepository.findByName(nameCourse);
        return new Topic(this.title, this.message, course);
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getNameCourse() {
        return nameCourse;
    }


}
