package com.ywd.blog.dao;

import com.ywd.blog.entity.BlogIndex;

public interface BlogIndexDao {
	
	public Integer add(BlogIndex blogIndex);
	
	public Integer delete(Integer blogId);
	
	public Integer update(BlogIndex blogIndex);
	
	public BlogIndex findByBlogId(Integer blogId);

}
