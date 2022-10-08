package com.leodemetrio.forum.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Data
@Entity
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

    public Topic(){}

    public Topic(String title, String message, Course course) {
        this.title = title;
        this.message = message;
        this.course = course;
    }
}
