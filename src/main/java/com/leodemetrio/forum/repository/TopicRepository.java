package com.leodemetrio.forum.repository;

import com.leodemetrio.forum.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;


public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByCourseName(String nameCourse);
}
