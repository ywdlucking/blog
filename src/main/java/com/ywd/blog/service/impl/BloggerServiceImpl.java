package com.ywd.blog.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ywd.blog.dao.BloggerDao;
import com.ywd.blog.entity.Blogger;
import com.ywd.blog.service.BloggerService;



@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService{

	@Autowired
	private BloggerDao bloggerDao;
	
	public Blogger getByUserName(String userName) {
		return bloggerDao.getByUserName(userName);
	}

	public Blogger findBlogger() {
		return bloggerDao.find();
	}

	@Override
	public int update(Blogger blogger) {
		return bloggerDao.update(blogger);
	}

}
