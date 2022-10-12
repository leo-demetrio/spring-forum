package com.leodemetrio.forum.model;


import com.leodemetrio.forum.dto.TopicRequestDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Data
@Entity
@RequiredArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime dataCreation = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusTopic status = StatusTopic.NOT_RESPONSE;

    @ManyToOne
    private User author;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "topic")
    private List<Answer> answers = new ArrayList<>();


    public Topic(String title, String message, Course course){
        this.title = title;
        this.message = message;
        this.course = course;
    }

    public Topic(Long id, String title, String message, LocalDateTime dataCreation, StatusTopic status, User author, Course course) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.dataCreation = dataCreation;
        this.status = status;
        this.author = author;
        this.course = course;
    }
}
