package com.api.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.exception.ResourceNotFoundException;
import com.api.model.Comment;
import com.api.model.Post;
import com.api.model.User;
import com.api.repository.CommentRepository;
import com.api.repository.PostRepository;
import com.api.repository.UserDetailsRepository;
@Service
public class CommentService {
	@Autowired
	 private CommentRepository commentRepository;
	@Autowired
	    private  PostRepository postRepository;
	@Autowired
	    private  UserDetailsRepository userRepository;



	    public List<Comment> getCommentsForPost(long postId) {
	        List<Comment> findByPost_PostId = commentRepository.findByPost_PostId(postId);
	    return findByPost_PostId;
	        }
	    
	
	
	    public Comment createComment(long postId, Comment comment, long userId) {
	        Post post = postRepository.findById(postId)
	                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
	        
	         User user = userRepository.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
	         Comment c =new Comment();
	         c.setText(comment.getText());
	        c.setUser(user);
	        c.setPost(post);
	        return commentRepository.save(c);
	    }

	    public Comment updateComment(long postId, long commentId, Comment updatedComment) {
	        Comment comment = commentRepository.findById(commentId)
	                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

	        if (comment.getPost().getPostId()!=(postId)) {
	            throw new IllegalArgumentException("Comment does not belong to the specified post");
	        }

	        comment.setText(updatedComment.getText());
	        return commentRepository.save(comment);
	    }
	    
	    

	    public void deleteComment(long postId, long commentId) {
	        Comment comment = commentRepository.findById(commentId)
	                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

	        if (comment.getPost().getPostId()!=(postId)) {
	            throw new IllegalArgumentException("Comment does not belong to the specified post");
	        }

	        commentRepository.delete(comment);
	    }
}
