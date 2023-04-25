/*
package com.example.ProjectBeehive.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "POST_COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private BigInteger id;

    @Column(name = "COMMENTER_ID", nullable = false)
    private BigInteger commenterId;

    @Column(name = "POSTER_ID", nullable = false)
    private BigInteger posterId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @Column(name = "DATE_TIME", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp dateTime;

    @Column(name = "EDITED", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean edited;

    @Column(name = "COMMENT_TEXT", nullable = false)
    private String commentText;

}*/
