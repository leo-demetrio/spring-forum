package com.leodemetrio.forum.controller;

import com.leodemetrio.forum.TopicDto;
import com.leodemetrio.forum.model.Course;
import com.leodemetrio.forum.model.Topic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicsController {


    @GetMapping
    public List<TopicDto> listAll(){
        return TopicDto.convert(Arrays.asList(new Topic("Dúvida", "Spring", new Course("Spring", "Programing"))));
    }
}
