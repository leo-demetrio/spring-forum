package com.leodemetrio.forum.controller;

import com.leodemetrio.forum.dto.TopicDetailsDto;
import com.leodemetrio.forum.dto.TopicDto;
import com.leodemetrio.forum.dto.TopicRequestPostDto;
import com.leodemetrio.forum.dto.TopicRequestPutDto;
import com.leodemetrio.forum.model.Topic;
import com.leodemetrio.forum.repository.CourseRepository;
import com.leodemetrio.forum.repository.TopicRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<TopicDto> create(@RequestBody @Valid TopicRequestPostDto topicPostDto, UriComponentsBuilder uriComponentsBuilder){
        Topic topic = topicPostDto.convertToTopic(courseRepository);
        URI uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDto(topicRepository.save(topic)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDetailsDto> findById(@PathVariable Long id){
        Optional<Topic> OptionalTopic = topicRepository.findById(id);
        return ResponseEntity.ok(new TopicDetailsDto(OptionalTopic.get()));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody TopicRequestPutDto topicDto){
        Topic topic = topicDto.update(id, topicRepository);

        return ResponseEntity.ok(new TopicDto(topic));
    }
}
