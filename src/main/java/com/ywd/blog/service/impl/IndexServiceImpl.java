package com.ywd.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ywd.blog.dao.BlogIndexDao;
import com.ywd.blog.entity.BlogIndex;
import com.ywd.blog.service.IndexService;

@Service("indexService")
public class IndexServiceImpl implements IndexService {
	
	@Autowired
	private BlogIndexDao blogindex;

	@Override
	public Integer add(BlogIndex blogIndex) {
		return blogindex.add(blogIndex);
	}

	@Override
	public Integer delete(Integer blogId) {
		return blogindex.delete(blogId);
	}

	@Override
	public Integer update(BlogIndex blogIndex) {
		return blogindex.update(blogIndex);
	}

	@Override
	public BlogIndex findByBlogId(Integer blogId) {
		return blogindex.findByBlogId(blogId);
	}

}
