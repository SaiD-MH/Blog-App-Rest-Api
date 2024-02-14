package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.springboot.blog.utils.AppConstants.*;
@RestController
@RequestMapping("/api/posts")
public class PostController {


    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create post

    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {

        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);

    }

    // Support Pagination
    @GetMapping
    public PostResponse getAllPosts(

            @RequestParam(value = "pageNo"   , defaultValue = DEFAULT_PAGE_NUMBER    , required = false) int pageNo ,
            @RequestParam(value = "pageSize" , defaultValue = DEFAULT_PAGE_SIZE      , required = false) int pageSize,
            @RequestParam(value = "sortBy"   , defaultValue = DEFAULT_SORT_BY        , required = false) String sortBy ,
            @RequestParam(value = "sortDir"  , defaultValue = DEFAULT_SORT_DIRECTION , required = false) String sortDir

    ) {

        return postService.getAllPosts(pageNo , pageSize , sortBy , sortDir);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long id) {

        return  ResponseEntity.ok(postService.getPostById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto , @PathVariable("id") long id){

        return ResponseEntity.ok(postService.updatePost(postDto , id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity <String > deletePost(@PathVariable("id") long id){

        postService.deletePost(id);

        return ResponseEntity.ok(String.format("Post with id %s deleted Successfully" , id));
    }


}
