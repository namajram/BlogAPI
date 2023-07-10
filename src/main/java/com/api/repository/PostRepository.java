package com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.model.Post;
import com.api.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
List<Post> findByUser_UserId(Long userId);
void deleteByUser(User user);
}
