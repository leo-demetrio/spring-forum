package com.leodemetrio.forum.repository;



import com.leodemetrio.forum.model.Course;
import com.leodemetrio.forum.model.Topic;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;



@Log4j2
@DataJpaTest
@ActiveProfiles("test")
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TestEntityManager em;


    @Test
    public void shouldLoadOneCourseSearchByName(){
        String nameCourse = "Spring Boot";
        Course course1 = new Course(nameCourse,"programing");
        em.persist(course1);
        Course course = this.courseRepository.findByName(nameCourse);
        log.info(course);
        Assertions.assertNotNull(course);
        Assertions.assertEquals(nameCourse, course.getName());
    }

    @Test
    public void NoShouldLoadOneCourseSearchByNameNotRegistered(){
        String nameCourse = "Spring MVC";
        Course course1 = new Course(nameCourse,"programing");
        em.persist(course1);
        Course course = this.courseRepository.findByName("Spring");
        log.info(course);
        Assertions.assertNull(course);
    }
}
