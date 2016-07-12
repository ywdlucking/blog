package com.ywd.blog.entity;

public class BlogIndex {
	
	private Integer id;
	private Integer blogId;
	private String blogTitle;
	private String noTagContent;
	
	public BlogIndex() {
	}
	public BlogIndex(Integer blogId, String blogTitle, String noTagContent) {
		this.blogId = blogId;
		this.blogTitle = blogTitle;
		this.noTagContent = noTagContent;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBlogId() {
		return blogId;
	}
	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getNoTagContent() {
		return noTagContent;
	}
	public void setNoTagContent(String noTagContent) {
		this.noTagContent = noTagContent;
	}
	
	
}
