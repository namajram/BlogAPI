package com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.model.Comment;
import com.api.model.Post;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
List<Comment> findByPost_PostId(Long postId);

void deleteByPost_PostId(Long postId);

}

