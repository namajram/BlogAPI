package com.api.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long commentId;

	    private String text;

	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "postCommentId" ,referencedColumnName = "postid")
	    private Post post;

	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name ="UserCommentId",referencedColumnName = "userId")
	    private User user;

	

		public Comment(long commentId, String text, Post post, User user) {
			super();
			this.commentId = commentId;
			this.text = text;
			this.post = post;
			this.user = user;
		}

		public long getCommentId() {
			return commentId;
		}

		public void setCommentId(long commentId) {
			this.commentId = commentId;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

	

		public Comment() {}

		public Post getPost() {
			return post;
		}

		public void setPost(Post post) {
			this.post = post;
		}


	

	
		
		
}
