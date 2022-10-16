package com.leodemetrio.forum.controller;

import com.leodemetrio.forum.dto.TopicDetailsDto;
import com.leodemetrio.forum.dto.TopicDto;
import com.leodemetrio.forum.dto.TopicRequestPostDto;
import com.leodemetrio.forum.dto.TopicRequestPutDto;
import com.leodemetrio.forum.model.Topic;
import com.leodemetrio.forum.repository.CourseRepository;
import com.leodemetrio.forum.repository.TopicRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
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
    @Cacheable(value = "listTopics")
    public Page<TopicDto> listAll(
            @RequestParam(value = "CourseName", required = false) String courseName,
            Pageable pageable
    ){
        if(courseName == null){
            return TopicDto.convert(topicRepository.findAll(pageable));
        }
        return TopicDto.convert(topicRepository.findByCourseName(courseName, pageable));
    }

    @PostMapping
    @CacheEvict(value = "listTopics", allEntries = true)
    public ResponseEntity<TopicDto> create(@RequestBody @Valid TopicRequestPostDto topicPostDto, UriComponentsBuilder uriComponentsBuilder){
        Topic topic = topicPostDto.convertToTopic(courseRepository);
        URI uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDto(topicRepository.save(topic)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDetailsDto> findById(@PathVariable Long id){
        Optional<Topic> OptionalTopic = topicRepository.findById(id);
        if(!OptionalTopic.isPresent()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new TopicDetailsDto(OptionalTopic.get()));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listTopics", allEntries = true)
    public  ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody TopicRequestPutDto topicDto){
        Optional<Topic> OptionalTopic = topicRepository.findById(id);
        if(!OptionalTopic.isPresent()) return ResponseEntity.notFound().build();
        Topic topic = topicDto.update(OptionalTopic.get());

        return ResponseEntity.ok(new TopicDto(topic));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listTopics", allEntries = true)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Optional<Topic> OptionalTopic = topicRepository.findById(id);
        if(!OptionalTopic.isPresent()) return ResponseEntity.notFound().build();
        topicRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
