package com.example.ProjectBeehive.controller;

import com.example.ProjectBeehive.payload.PostDto;
import com.example.ProjectBeehive.security.JwtTokenProvider;
import com.example.ProjectBeehive.service.PostService;
import com.example.ProjectBeehive.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private PostService postService;
    private UserService userService;
    private JwtTokenProvider jwtTokenProvider;

    public PostController(PostService postService, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.postService = postService;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @PostMapping("/upload")
    public ResponseEntity<PostDto> uploadPost(@Valid @RequestBody PostDto postDto, @RequestHeader("Authorization") String token){
        // Remove the Bearer prefix from the token
        String authToken = token.substring(7);

        // Validate the token
        jwtTokenProvider.validateToken(authToken);

        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @PutMapping("/editPost")
    public ResponseEntity<PostDto> editPost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") BigInteger id){
        PostDto postResponse = postService.updatePost(postDto, id);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("/deletePost")
    public ResponseEntity<String> deletePost(@RequestParam("username") String username, @Valid @RequestHeader("Authorization") String token){
        // Remove the Bearer prefix from the token
        String authToken = token.substring(7);

        // Validate the token
        jwtTokenProvider.validateToken(authToken);
        postService.deletePostById(postService.getRecentPostId(username));

        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }

    @GetMapping("/findUserPosts")
    public ResponseEntity<List<PostDto>> getPosts(@RequestParam("username") String username, @Valid @RequestHeader("Authorization") String token){
        // Remove the Bearer prefix from the token
        String authToken = token.substring(7);

        // Validate the token
        jwtTokenProvider.validateToken(authToken);
        List<PostDto> postDtos = postService.getPostsByUserId(userService.findIdByUsername(username));
        return ResponseEntity.ok(postDtos);
    }

    @PostMapping("/comment")
    public void commentPost(){

    }

    @PutMapping("/editComment")
    public void editComment(){

    }

    @DeleteMapping("/deleteComment")
    public void deleteCommend(){

    }

}
