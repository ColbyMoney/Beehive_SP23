package com.example.ProjectBeehive.service;

import com.example.ProjectBeehive.payload.PostDto;
import com.example.ProjectBeehive.payload.PostResponse;

import java.math.BigInteger;
import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    List<PostDto> getPostsByUserId(BigInteger userId);

    PostDto updatePost(PostDto postDto, BigInteger id);

    void deletePostById(BigInteger id);

    BigInteger getRecentPostId(String username);
}
