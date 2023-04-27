package com.example.ProjectBeehive.payload;

import lombok.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class PostDto {
    private BigInteger POSTS_ID;

    private String username;

    // title should not be null  or empty
    // title should have at least 2 characters
    @NotEmpty
    @Size(min = 2, message = "Post should have an image")
    private byte[] image;

    // post description should be not null or empty
    // post description should have at least 10 characters
    @NotEmpty
    @Size(min = 10, message = "Post should have a date and time")
    private LocalDateTime createdAt;

    // post content should not be null or empty
    //private Set<CommentDto> comments;
}
