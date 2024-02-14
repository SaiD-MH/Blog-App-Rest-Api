package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "body")
    private String body;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;



}
