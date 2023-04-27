package com.example.ProjectBeehive.service.impl;

import com.example.ProjectBeehive.entity.Post;
import com.example.ProjectBeehive.exception.ResourceNotFoundException;
import com.example.ProjectBeehive.payload.PostDto;
import com.example.ProjectBeehive.payload.PostResponse;
import com.example.ProjectBeehive.repository.PostRepository;
import com.example.ProjectBeehive.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private ModelMapper mapper;

    private UserServiceImpl userRepository;

    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper, UserServiceImpl userRepository) {
        this.postRepository = postRepository;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        // convert DTO to entity
        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);

        // convert entity to DTO
        PostDto postResponse = mapToDTO(newPost);
        return postResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        // get content for page object
        List<Post> listOfPosts = posts.getContent();

        List<PostDto> content= listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public List<PostDto> getPostsByUserId(BigInteger userId) {
        List<Post> posts = postRepository.findByUserIdOrderByCreatedAtDesc(userId);
        return posts.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public PostDto updatePost(PostDto postDto, BigInteger id) {
        // get post by id from the database
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));


        post.setPOSTS_ID(postDto.getPOSTS_ID());
        post.setUserId((userRepository.findIdByUsername(postDto.getUsername())));
        post.setImage(postDto.getImage());
        post.setCreatedAt(postDto.getCreatedAt());
        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePostById(BigInteger id) {
        // get post by id from the database
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    @Override
    public BigInteger getRecentPostId(String username) {
        BigInteger userId = userRepository.findIdByUsername(username);
        List<Post> posts = postRepository.findPostsByUserIdOrderByCreatedAtDesc(userId);
        if (!posts.isEmpty()) {
            return posts.get(0).getPOSTS_ID();
        } else {
            throw new ResourceNotFoundException("Post", "username", BigInteger.valueOf(300));
        }
    }

/*    @Override
    public List<PostDto> getPostsByCategory(Long categoryId) {

        List<Post> posts = postRepository.findByCategoryId(categoryId);

        return posts.stream().map((post) -> mapToDTO(post))
                .collect(Collectors.toList());
    }*/

    // convert Entity into DTO
    private PostDto mapToDTO(Post post){
        PostDto postDto = mapper.map(post, PostDto.class);
//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
        return postDto;
    }

    // convert DTO to entity
    private Post mapToEntity(PostDto postDto){
        Post post = mapper.map(postDto, Post.class);
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;
    }

}
