package com.ywd.blog.entity;

import java.util.Date;

public class ReComment {
	
	private Integer id;
	private String reComment;
	private Integer commentId;
	private Date reCommentDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReComment() {
		return reComment;
	}
	public void setReComment(String reComment) {
		this.reComment = reComment;
	}
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Date getReCommentDate() {
		return reCommentDate;
	}
	public void setReCommentDate(Date reCommentDate) {
		this.reCommentDate = reCommentDate;
	}
	
}
