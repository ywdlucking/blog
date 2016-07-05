package com.ywd.blog.dao;

import com.ywd.blog.entity.Blogger;

public interface BloggerDao {

	public Blogger getByUserName(String userName);
	
	public Blogger find();
	
	public int update(Blogger blogger);
}
