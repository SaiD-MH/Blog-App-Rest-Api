package com.springboot.blog.service;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.payload.CommentDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentService {


    CommentDto createComment(CommentDto commentDto , Long postId);


    List<CommentDto> getAllCommentsByPostId(Long id);

    void deleteCommentById(long postId , long commentId);

    CommentDto getCommentById(long postId , long commentId);

    CommentDto updateComment(CommentDto commentDto , long postId , long commentId);;

}
