package com.api.Service.impl;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.exception.ResourceNotFoundException;
import com.api.model.Comment;
import com.api.model.Post;
import com.api.model.PostPlayload;
import com.api.model.User;
import com.api.repository.CommentRepository;
import com.api.repository.PostRepository;
import com.api.repository.UserDetailsRepository;

@Service
public class PostService {
	@Autowired
	 private  PostRepository postRepository;

	  @Autowired
	  private UserDetailsRepository userDetailsRepository;
	  
    @Autowired
	  private CommentRepository commentRepository;
	    
	    public List<Post> getAllPosts() {
	        return postRepository.findAll();
	    }
	    
	    public List<Post> getPostByUserId(long userId) {
	    	List<Post> findByUser_UserId = postRepository.findByUser_UserId(userId);
	    	return findByUser_UserId;
	    }
	    
	public Post createPost(Post post, long usedId) {
		User user = userDetailsRepository.findById(usedId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		Post p = new Post();
				p.setDescription(post.getDescription());
				p.setTitle(post.getTitle());
				p.setUser(user);
				
        return postRepository.save(p);
    }

    public Post getPostById(long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
    }

    public Post updatePost(long postId, Post updatedPost) {
        Post post = getPostById(postId);
        post.setTitle(updatedPost.getTitle());
        post.setDescription(updatedPost.getDescription());

        return postRepository.save(post);
    }

  
    
    public void likePost(long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        post.setLikes(post.getLikes() + 1);
        postRepository.save(post);
    }
    public void removeLikePost(long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        post.setLikes(post.getLikes()-1);
        postRepository.save(post);
    }
    
    public Post create(PostPlayload post, long usedId,MultipartFile file)throws Throwable {
		User user = userDetailsRepository.findById(usedId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		Post p = new Post();
				p.setDescription(post.getDescription());
				p.setTitle(post.getTitle());
				p.setUser(user);
				byte[] bytes = file.getBytes();
				String encodeToString = Base64.getEncoder().encodeToString(bytes);
				
				p.setImage(encodeToString);
        return postRepository.save(p);
    }
    
    public void deletePost(long postId) {
//    	commentRepository.deleteByPost_PostId(postId);
    	
//        postRepository.deleteById(postId);
    	 Post post = postRepository.findById(postId)
                 .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
    	 List<Comment> comments = post.getComments();
    	 commentRepository.deleteAll(comments);
    	       
         
         postRepository.deleteById(postId);
    }
}
