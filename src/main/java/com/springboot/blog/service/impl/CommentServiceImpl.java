package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogAppException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {


    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper ;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository , ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId) {


        // Find Post First

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId)
        );

        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);

        return mapToDto(savedComment);
    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(Long postId) {

        // find post First

        List<Comment> comments = commentRepository.findByPostId(postId);

        List<CommentDto> commentsDtoList = comments.stream().map(comment -> mapToDto(comment))
                .collect(Collectors.toList());
        return commentsDtoList;
    }

    @Override
    public void deleteCommentById(long postId, long commentId) {

        // find the post

        Post post = postRepository.findById(postId).orElseThrow(

                () -> new ResourceNotFoundException("posts", "id", postId)

        );

        // find comment by id

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment", "comment-id", commentId)
        );

        // Check if comment belong to the post


        if (comment.getPost().getId() != postId  )
            throw new BlogAppException(HttpStatus.BAD_REQUEST , "Comment doesn't belong to the post");


        commentRepository.delete(comment);
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {

        // find the post

        Post post = postRepository.findById(postId).orElseThrow(

                () -> new ResourceNotFoundException("posts", "id", postId)

        );

        // find comment by id

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment", "comment-id", commentId)
        );

        // Check if comment belong to the post


        if (comment.getPost().getId() != postId  )
            throw new BlogAppException(HttpStatus.BAD_REQUEST , "Comment doesn't belong to the post");

        return mapToDto(comment);

    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, long postId, long commentId) {

        // find the post

        Post post = postRepository.findById(postId).orElseThrow(

                () -> new ResourceNotFoundException("posts", "id", postId)

        );

        // find comment by id

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment", "comment-id", commentId)
        );

        // Check if comment belong to the post


        if (comment.getPost().getId() != postId  )
            throw new BlogAppException(HttpStatus.BAD_REQUEST , "Comment doesn't belong to the post");

        // update the saved Comment
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);
    }


    private CommentDto mapToDto(Comment comment) {
        // Convert Entity to Dto

        CommentDto commentDto = modelMapper.map(comment , CommentDto.class);
        return commentDto;

    }

    private Comment mapToEntity(CommentDto commentDto) {

        // Convert Dto to Entity

        Comment comment = modelMapper.map(commentDto , Comment.class);
        return comment;
    }


}
