package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {


        // map dto -> entity
        Post post = mapToEntity(postDto);

        Post newPost = postRepository.save(post);

        // map entity -> dto
        PostDto responsePost = mapToDto(newPost);

        return responsePost;
    }

    @Override
    public List<PostDto> getAllPosts() {

        List<Post> posts = postRepository.findAll();

        return posts.stream()
                .map(post -> mapToDto(post))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long id) {

        Post post = postRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("posts","id",id) );

        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {


        Post savedPost = postRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("posts","id",id)
        );

        savedPost.setTitle(postDto.getTitle());
        savedPost.setDescription(postDto.getDescription());
        savedPost.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(savedPost);

        return mapToDto(updatedPost);
    }

    @Override
    public void deletePost(Long id) {

        Post post = postRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("posts","id",id)
        );

        postRepository.delete(post);

    }


    private Post mapToEntity(PostDto postDto) {

        // Convert DTO to Entity

        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return post;

    }

    private PostDto mapToDto(Post post) {

        // Convert Entity to DTO

        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());

        return postDto;
    }


}
