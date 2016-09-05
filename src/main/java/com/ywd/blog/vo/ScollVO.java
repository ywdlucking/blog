package com.ywd.blog.vo;

public class ScollVO {
	
	private String id;
	private String client; 
	private String desc;
	
	public ScollVO(String id, String client, String desc) {
		this.id = id;
		this.client = client;
		this.desc = desc;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
