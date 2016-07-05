package com.ywd.blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ywd.blog.dao.BlogDao;
import com.ywd.blog.entity.Blog;
import com.ywd.blog.service.BlogService;

@Service("blogService")
public class BlogServiceImpl implements BlogService {
	
	@Resource
	private BlogDao blogDao;

	public List<Blog> countList() {
		return blogDao.countList();
	}

	public List<Blog> list(Map<String, Object> map) {
		return blogDao.list(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return blogDao.getTotal(map);
	}

	public Blog findById(Integer id) {
		return blogDao.findById(id);
	}

	public Integer update(Blog blog) {
		return blogDao.update(blog);
	}

	public Blog getLastBlog(Integer id) {
		return blogDao.getLastBlog(id);
	}

	public Blog getNextBlog(Integer id) {
		return blogDao.getNextBlog(id);
	}

	public int add(Blog blog) {
		return blogDao.add(blog);
	}

	public Integer deleteBlog(Integer id) {
		return blogDao.deleteBlog(id);
	}

	
}
