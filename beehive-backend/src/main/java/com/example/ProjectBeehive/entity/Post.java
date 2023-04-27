package com.example.ProjectBeehive.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private BigInteger POSTS_ID;

    @ManyToOne // Add this annotation to map the relationship
    @JoinColumn(name = "USER_ID", nullable = false) // Add this annotation to specify the foreign key column
    private User user; // Change this field from userId to user

    @Lob
    @Column(nullable = false, name = "IMAGE")
    private byte[] image;

    @Column(nullable = false, name = "CREATED_AT")
    private LocalDateTime createdAt;

/*    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();*/
}
