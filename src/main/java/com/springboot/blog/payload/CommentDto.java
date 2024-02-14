package com.springboot.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private long id;
    @NotEmpty(message = "comment body can't be empty")
    private String body;
    @Email
    private String email;

    @NotEmpty(message = "name can't be empty")
    private String name;



}
