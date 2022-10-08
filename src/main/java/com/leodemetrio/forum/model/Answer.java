package com.leodemetrio.forum.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @ManyToOne
    private Topic topic;
    private LocalDateTime dataCreation = LocalDateTime.now();
    @ManyToOne
    private User author;
    private Boolean solution = false;

}
