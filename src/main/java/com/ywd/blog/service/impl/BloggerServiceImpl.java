package com.ywd.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ywd.blog.dao.BloggerDao;
import com.ywd.blog.entity.Blogger;
import com.ywd.blog.service.BloggerService;



@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService{

	@Resource
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
