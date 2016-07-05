package com.ywd.blog.service;

import com.ywd.blog.entity.Blogger;

public interface BloggerService {

	public Blogger getByUserName(String userName);
	
	public Blogger findBlogger();
	
	public int update(Blogger blogger);
}
