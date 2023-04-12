package com.example.ProjectBeehive.entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "POSTS")
public class File {

    //@Lob
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private BigInteger POSTS_ID;

}
