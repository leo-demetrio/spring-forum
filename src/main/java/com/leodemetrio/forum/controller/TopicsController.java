package com.leodemetrio.forum.controller;

import com.leodemetrio.forum.TopicDto;
import com.leodemetrio.forum.model.Course;
import com.leodemetrio.forum.model.Topic;
import com.leodemetrio.forum.repository.CourseRepository;
import com.leodemetrio.forum.repository.TopicRepository;
import com.leodemetrio.forum.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    private TopicRepository repository;


    public TopicsController(TopicRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<TopicDto> listAll(){
        return TopicDto.convert(repository.findAll());
    }
}
