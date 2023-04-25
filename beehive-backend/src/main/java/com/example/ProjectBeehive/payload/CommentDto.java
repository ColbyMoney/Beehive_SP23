package com.example.ProjectBeehive.payload;

import com.example.ProjectBeehive.entity.Post;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class CommentDto {
    private BigInteger commentId;
    private BigInteger commenterId;
    private BigInteger posterId;
    private BigInteger postId;
    private Timestamp commentDateTime;
    private Boolean commentEdited;
    private Post post;
    // comment body should not be bull or empty
    // Comment body must be minimum 10 characters
    @NotEmpty
    @Size(min = 10, message = "Comment body must be minimum 10 characters")
    private String commentText;
}