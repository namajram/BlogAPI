package com.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.Service.impl.PostService;
import com.api.model.Post;
import com.api.model.PostPlayload;

@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired
	private  PostService postService;

   
    
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
    
    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<Post>> getPostByUser(@PathVariable long userId){
    	List<Post> postByUserId = postService.getPostByUserId(userId);
    	return ResponseEntity.ok(postByUserId);
    }
    
    @PostMapping("/createPost/{userId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable long userId) {
        Post createdPost = postService.createPost(post,userId);
        return ResponseEntity.ok(createdPost);
    }

    @GetMapping("/getId/{postId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Post> getPostById(@PathVariable long postId) {
        Post post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{postId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Post> updatePost(@PathVariable long postId, @RequestBody Post post) {
        Post updatedPost = postService.updatePost(postId, post);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/delete/{postId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deletePost(@PathVariable long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("Post deleted successfully.");
    }
    
    @PostMapping("/{postId}/like")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> likePost(@PathVariable long postId) {
        postService.likePost(postId);
        return ResponseEntity.ok("Post liked successfully.");
    }
    @PostMapping("/{postId}/removelike")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> removelikePost(@PathVariable long postId) {
        postService.removeLikePost(postId);
        return ResponseEntity.ok("Post like removed successfully.");
    }

    @PostMapping("/create/{userId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Post> create(@ModelAttribute  PostPlayload post, @PathVariable long userId, @RequestParam("image") MultipartFile file) throws Throwable {
        Post createdPost = postService.create(post,userId,file);
        return ResponseEntity.ok(createdPost);
    }

}
