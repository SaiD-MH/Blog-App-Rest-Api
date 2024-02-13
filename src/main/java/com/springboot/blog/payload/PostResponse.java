package com.springboot.blog.payload;

import com.springboot.blog.entity.Post;
import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private List<PostDto> posts;
    private int PageSize ;
    private int pageNo ;

    private long totalElement;
    private int totalPages;
    private boolean last;


}
