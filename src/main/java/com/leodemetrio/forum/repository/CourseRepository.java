package com.leodemetrio.forum.repository;

import com.leodemetrio.forum.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
