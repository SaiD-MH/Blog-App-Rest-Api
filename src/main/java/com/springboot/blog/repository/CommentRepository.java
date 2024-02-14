package com.springboot.blog.repository;

import com.springboot.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment , Long> {

    /*
    @Query(value = "select * from comments where post_id =:id" , nativeQuery = true)
    List<Comment> getAllComments(Long id);

    */
    List<Comment> findByPostId(Long PostId);


}
