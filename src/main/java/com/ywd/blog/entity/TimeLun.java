package com.ywd.blog.entity;

import java.util.Date;

public class TimeLun {
	
	private Integer id;
	private String context;
	private Date saydate;
	private String picture;
	private Integer syear;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Date getSaydate() {
		return saydate;
	}
	public void setSaydate(Date saydate) {
		this.saydate = saydate;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Integer getSyear() {
		return syear;
	}
	public void setSyear(Integer syear) {
		this.syear = syear;
	}
}
