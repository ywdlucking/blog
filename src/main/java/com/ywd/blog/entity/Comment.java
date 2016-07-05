package com.ywd.blog.entity;

import java.util.Date;

public class Comment {

	private Integer id; // 编号
	private String commentName; // 评论用户名
	private String email; // 评论用户邮箱
	private String userIp; // 用户IP
	private String content; // 评论内容
	private Blog blog; // 被评论的博客
	private Date commentDate; // 评论日期
	private Integer state; // 审核状态 0 待审核 1 审核通过 2 审核未通过
	private ReComment reComment; //回复评论
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCommentName() {
		return commentName;
	}
	public void setCommentName(String commentName) {
		this.commentName = commentName;
	}
	public ReComment getReComment() {
		return reComment;
	}
	public void setReComment(ReComment reComment) {
		this.reComment = reComment;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
