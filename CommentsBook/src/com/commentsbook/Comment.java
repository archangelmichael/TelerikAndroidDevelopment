package com.commentsbook;

public class Comment {
	private long id;
	private String comment;

	public Comment(Long id, String comment) {
		this.setId(id);
		this.setComment(comment);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return comment;
	}
}
