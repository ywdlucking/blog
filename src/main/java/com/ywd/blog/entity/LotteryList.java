package com.ywd.blog.entity;

import java.util.Date;

public class LotteryList {
	
	private Long id;	//
	
	private Integer rank;	//抽奖级别
	
	private String userNo;	//用户编号
	
	private String userName;	//用户姓名
	
	private Date cdate;	//

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
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

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	
}
