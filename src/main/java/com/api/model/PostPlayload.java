package com.api.model;

public class PostPlayload {
	 private String title;
	    private String description;
	    private int likes;
		public PostPlayload() {
			super();
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public int getLikes() {
			return likes;
		}
		public void setLikes(int likes) {
			this.likes = likes;
		}
		public PostPlayload(String title, String description, int likes) {
			super();
			this.title = title;
			this.description = description;
			this.likes = likes;
		}
	    
}
