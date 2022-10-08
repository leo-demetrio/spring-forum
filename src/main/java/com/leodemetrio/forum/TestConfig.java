package com.leodemetrio.forum;

import com.leodemetrio.forum.model.Course;
import com.leodemetrio.forum.model.Topic;
import com.leodemetrio.forum.repository.CourseRepository;
import com.leodemetrio.forum.repository.TopicRepository;
import com.leodemetrio.forum.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration("test")
public class TestConfig implements CommandLineRunner {

    private TopicRepository repository;
    private UserRepository userRepository;
    private CourseRepository courseRepository;

    public TestConfig(TopicRepository repository, UserRepository userRepository, CourseRepository courseRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public void run(String... args) throws Exception{
        var c = courseRepository.save(new Course("Spring", "Programing"));
        repository.save(new Topic("Dúvida", "Spring", c));
    }
}
