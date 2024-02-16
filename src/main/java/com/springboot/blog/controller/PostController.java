package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.springboot.blog.utils.AppConstants.*;

@RestController
@RequestMapping("/api/v1/posts")
@Tag(name = "CRUD REST APIs for Post Resource")
public class PostController {


    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create post

    @PostMapping
    @Operation(

            description = "Post Method for Create new post take ( Post ) as parameter ",
            summary = "Create new Post ",
            responses = {
                    @ApiResponse(
                            description = "CREATED",
                            responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "Unauthorized or Invalid Token",
                            responseCode = "401"
                    )
            }
    )
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {

        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);

    }

    // Support Pagination
    @GetMapping
    @Operation(

            description = "Get Method for list all posts with comments they have",
            summary = "List Posts with their comments",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized or Invalid Token",
                            responseCode = "401"
                    )
            }
    )
    public PostResponse getAllPosts(

            @RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir

    ) {

        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }


    @GetMapping("/{id}")
    @Operation(

            description = "Get Method for Post Details by id take (postId) as Parameter",
            summary = "Get Post with it's comment By ID",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized or Invalid Token",
                            responseCode = "401"
                    )
            }
    )
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long id) {

        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    @Operation(

            description = "Update Post with (PostID) as parameter",
            summary = "Update Post ",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized or Invalid Token",
                            responseCode = "401"
                    )
            }
    )
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable("id") long id) {

        return ResponseEntity.ok(postService.updatePost(postDto, id));
    }


    @DeleteMapping("/{id}")
    @Operation(

            description = "Delete Method for Deleting Post by id take (postId) as Parameter",
            summary = "Delete Post",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized or Invalid Token",
                            responseCode = "401"
                    )
            }
    )
    public ResponseEntity<String> deletePost(@PathVariable("id") long id) {

        postService.deletePost(id);

        return ResponseEntity.ok(String.format("Post with id %s deleted Successfully", id));
    }


}
