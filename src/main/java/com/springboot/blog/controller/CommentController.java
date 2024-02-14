package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {


    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto, @PathVariable("postId") Long postId) {
        return new ResponseEntity<>(
                commentService.createComment(commentDto, postId)
                , HttpStatus.CREATED
        );
    }


    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable("postId") long postId) {

        return ResponseEntity.ok(commentService.getAllCommentsByPostId(postId));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("postId") long postId
            , @PathVariable("commentId") long commentId) {

        return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
    }


    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @Valid @RequestBody CommentDto commentDto,
            @PathVariable("postId") long postId,
            @PathVariable("commentId") long commentId

    ) {


        return ResponseEntity.ok(commentService.updateComment(commentDto, postId, commentId));

    }


    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("postId") long postId
            , @PathVariable("commentId") long commentId) {
        commentService.deleteCommentById(postId, commentId);

        return ResponseEntity.ok(
                String.format("Comment of Post:%s with commentId:%s Deleted Successfully",
                        postId, commentId)
        );

    }


}
