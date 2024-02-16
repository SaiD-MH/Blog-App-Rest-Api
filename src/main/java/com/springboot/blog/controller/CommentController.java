package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
@Tag(name = "CRUD REST APIs for Comment Resource")
public class CommentController {


    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping

    @Operation(

            description = "Create new Comment of Single Post (PostID)  ",
            summary = " Create New Comment ",
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
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto, @PathVariable("postId") Long postId) {
        return new ResponseEntity<>(
                commentService.createComment(commentDto, postId)
                , HttpStatus.CREATED
        );
    }


    @GetMapping
    @Operation(

            description = "Get Method for Comment of specific post by (id) take (postId) as Parameter",
            summary = "Get All Comments of Post(id) ",
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
    public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable("postId") long postId) {

        return ResponseEntity.ok(commentService.getAllCommentsByPostId(postId));
    }

    @GetMapping("/{commentId}")
    @Operation(

            description = "Get Method for Specific Comment (commentId)  of Single Post(PostID) ",
            summary = "Get  Single Comment of Post(id) ",
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
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("postId") long postId
            , @PathVariable("commentId") long commentId) {

        return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
    }


    @PutMapping("/{commentId}")
    @Operation(

            description = "Update Method for Specific Comment (commentId)  of Single Post(PostID) ",
            summary = "Update Single Comment of Post(id) ",
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
    public ResponseEntity<CommentDto> updateComment(
            @Valid @RequestBody CommentDto commentDto,
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId

    ) {


        return ResponseEntity.ok(commentService.updateComment(commentDto, postId, commentId));

    }


    @DeleteMapping("/{commentId}")
    @Operation(

            description = "Delete Method for Specific Comment (commentId)  of Single Post(PostID) ",
            summary = "Delete  Single Comment of Post(id) ",
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
    public ResponseEntity<String> deleteComment(@PathVariable("postId") long postId
            , @PathVariable("commentId") long commentId) {
        commentService.deleteCommentById(postId, commentId);

        return ResponseEntity.ok(
                String.format("Comment of Post:%s with commentId:%s Deleted Successfully",
                        postId, commentId)
        );

    }


}
