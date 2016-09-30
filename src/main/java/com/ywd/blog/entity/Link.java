package com.ywd.blog.entity;


public class Link {

	private Integer id; 
	private String linkName; 
	private String linkUrl;
	private Integer orderNo;
	private String linkDesc;
	private Integer state;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getLinkDesc() {
		return linkDesc;
	}
	public void setLinkDesc(String linkDesc) {
		this.linkDesc = linkDesc;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
