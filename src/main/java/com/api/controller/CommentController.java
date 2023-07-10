package com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.Service.impl.CommentService;
import com.api.model.Comment;
@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private  CommentService commentService;

   
	 @GetMapping("/postComments/{postId}")
	 @PreAuthorize("hasAuthority('ROLE_USER')")
	    public ResponseEntity<List<Comment>> getCommentsForPost(@PathVariable long postId) {
	        List<Comment> comments = commentService.getCommentsForPost(postId);
	        return ResponseEntity.ok(comments);
	    }
   

	 @PostMapping("/{postId}/{userId}")
	 @PreAuthorize("hasAuthority('ROLE_USER')")
	    public ResponseEntity<Comment> createComment(@PathVariable long postId, @RequestBody Comment comment,@PathVariable long userId) {
	        Comment createdComment = commentService.createComment(postId, comment,userId);
	        return ResponseEntity.ok(createdComment);
	    }

	    @PutMapping("/{commentId}/{postId}")
	    @PreAuthorize("hasAuthority('ROLE_USER')")
	    public ResponseEntity<Comment> updateComment(@PathVariable long postId, @PathVariable long commentId, @RequestBody Comment comment) {
	        Comment updatedComment = commentService.updateComment(postId, commentId, comment);
	        return ResponseEntity.ok(updatedComment);
	    }

	    @DeleteMapping("/{commentId}/{postId}")
	    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	    public ResponseEntity<String> deleteComment(@PathVariable long postId, @PathVariable long commentId) {
	        commentService.deleteComment(postId, commentId);
	        return ResponseEntity.ok("Comment deleted successfully.");
	    }
}
