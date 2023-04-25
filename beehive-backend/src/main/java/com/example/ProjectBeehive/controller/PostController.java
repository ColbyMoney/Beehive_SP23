package com.example.ProjectBeehive.controller;

import com.example.ProjectBeehive.payload.PostDto;
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

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping("/upload")
    public ResponseEntity<PostDto> uploadPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @PutMapping("/editPost")
    public ResponseEntity<PostDto> editPost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") BigInteger id){
        PostDto postResponse = postService.updatePost(postDto, id);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("/deletePost")
    public ResponseEntity<String> deletePost(@RequestParam(name = "username") String username){
        postService.deletePostById(postService.getRecentPostId(username));

        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }

    @GetMapping("/findUserPosts")
    public ResponseEntity<List<PostDto>> getPosts(@RequestParam("username") String username){
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
