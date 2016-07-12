package com.ywd.blog.service;

import com.ywd.blog.entity.BlogIndex;

public interface IndexService {

	public Integer add(BlogIndex blogIndex);
	
	public Integer delete(Integer blogId);
	
	public Integer update(BlogIndex blogIndex);
	
	public BlogIndex findByBlogId(Integer blogId);
}
