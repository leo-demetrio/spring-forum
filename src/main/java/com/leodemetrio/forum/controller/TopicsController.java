package com.leodemetrio.forum.controller;

import com.leodemetrio.forum.dto.TopicDto;
import com.leodemetrio.forum.dto.TopicRequestDto;
import com.leodemetrio.forum.model.Topic;
import com.leodemetrio.forum.repository.CourseRepository;
import com.leodemetrio.forum.repository.TopicRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topics")
@Log4j2
public class TopicsController {

    private final TopicRepository topicRepository;
    private final CourseRepository courseRepository;


    public TopicsController(TopicRepository repository, CourseRepository courseRepository) {
        this.topicRepository = repository;
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public List<TopicDto> listAll(@RequestParam(value = "CourseName", required = false) String courseName){
        if(courseName == null){
            return TopicDto.convert(topicRepository.findAll());
        }
        return TopicDto.convert(topicRepository.findByCourseName(courseName));
    }

    @PostMapping
    public ResponseEntity<TopicDto> create(@RequestBody TopicRequestDto topicPostDto, UriComponentsBuilder uriComponentsBuilder){
        Topic topic = topicPostDto.convertToTopic(courseRepository);
        URI uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDto(topicRepository.save(topic)));
    }
}
