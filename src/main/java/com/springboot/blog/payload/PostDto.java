package com.springboot.blog.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class PostDto {


    private Long id;

    @NotEmpty
    @Size(min = 5 , message = "title must have at least 5 char")
    private String title;
    @NotEmpty
    @Size(min = 10 , message = "post description must have atlest 10 chars")
    private String description;
    @NotEmpty
    private String content;
    private Set<CommentDto>comments;

}

