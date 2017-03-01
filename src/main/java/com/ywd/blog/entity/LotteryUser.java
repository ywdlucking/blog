package com.ywd.blog.entity;

import java.util.Date;

public class LotteryUser {
	
	private Long id;	//
	
	private String userNo;	//用户编号
	
	private String userName;	//用户姓名
	
	private String userImg;	//用户图片
	
	private Date cdate;	//

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	
}
