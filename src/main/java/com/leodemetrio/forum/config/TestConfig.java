package com.leodemetrio.forum.config;

import com.leodemetrio.forum.model.*;
import com.leodemetrio.forum.repository.CourseRepository;
import com.leodemetrio.forum.repository.ProfileRepository;
import com.leodemetrio.forum.repository.TopicRepository;
import com.leodemetrio.forum.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration("test")
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    private final ProfileRepository profileRepository;


//    public TestConfig(TopicRepository topicRepository, UserRepository userRepository, CourseRepository courseRepository) {
//        this.topicRepository = topicRepository;
//        this.userRepository = userRepository;
//        this.courseRepository = courseRepository;
//    }

    public void run(String... args) throws Exception{
        var password = new BCryptPasswordEncoder().encode("123");

        var a = new User(null,"Leo","leo@gmail.com",password);
        var a1 = new User(null,"Levi","levi@gmail.com",password);

        var p = new Profile(1L, "ROLE_STUDENT");
        var p1 = new Profile(2L, "ROLE_MODERATOR");

        var c = new Course(null,"Spring","Programing");
        var c1 = new Course(null,"Spring Boot", "Programing");
        var c2 = new Course(null,"Spring Cloud", "DevOps");
        var c3 = new Course(null,"DevOps", "DevOps");

        var t = new Topic(null,"Dúvida", "Spring Cloud", LocalDateTime.now(), StatusTopic.NOT_RESPONSE, a, c2);
        var t1 = new Topic(null,"Dúvida", "Spring Boot", LocalDateTime.now(), StatusTopic.NOT_RESPONSE, a, c1);
        var t2 = new Topic(null,"Dúvida", "Spring", LocalDateTime.now(), StatusTopic.NOT_RESPONSE, a, c);
        var t3 = new Topic(null,"Dúvida", "Devops", LocalDateTime.now(), StatusTopic.NOT_RESPONSE, a, c3);

        userRepository.saveAll(Arrays.asList(a,a1));
        profileRepository.saveAll(Arrays.asList(p,p1));
        courseRepository.saveAll(Arrays.asList(c,c1,c2,c3));
        topicRepository.saveAll(Arrays.asList(t,t1,t2,t3));
    }
}
