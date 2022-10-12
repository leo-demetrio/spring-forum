package com.leodemetrio.forum.config;

import com.leodemetrio.forum.model.Course;
import com.leodemetrio.forum.model.StatusTopic;
import com.leodemetrio.forum.model.Topic;
import com.leodemetrio.forum.model.User;
import com.leodemetrio.forum.repository.CourseRepository;
import com.leodemetrio.forum.repository.TopicRepository;
import com.leodemetrio.forum.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration("test")
//@AllArgsConstructor
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;


//    public TestConfig(TopicRepository topicRepository, UserRepository userRepository, CourseRepository courseRepository) {
//        this.topicRepository = topicRepository;
//        this.userRepository = userRepository;
//        this.courseRepository = courseRepository;
//    }

    public void run(String... args) throws Exception{

        var a = new User(null,"Leo","leo@gmail.com","123");
        var a1 = new User(null,"Levi","levi@gmail.com","123");

        var c = new Course(null,"Spring","Programing");
        var c1 = new Course(null,"Spring Boot", "Programing");
        var c2 = new Course(null,"Spring Cloud", "DevOps");
        var c3 = new Course(null,"DevOps", "DevOps");

        var t = new Topic(null,"Dúvida", "Spring Cloud", LocalDateTime.now(), StatusTopic.NOT_RESPONSE, a, c2);
        var t1 = new Topic(null,"Dúvida", "Spring Boot", LocalDateTime.now(), StatusTopic.NOT_RESPONSE, a, c1);
        var t2 = new Topic(null,"Dúvida", "Spring", LocalDateTime.now(), StatusTopic.NOT_RESPONSE, a, c);
        var t3 = new Topic(null,"Dúvida", "Devops", LocalDateTime.now(), StatusTopic.NOT_RESPONSE, a, c3);

        userRepository.saveAll(Arrays.asList(a,a1));
        courseRepository.saveAll(Arrays.asList(c,c1,c2,c3));
        topicRepository.saveAll(Arrays.asList(t,t1,t2,t3));
    }
}
