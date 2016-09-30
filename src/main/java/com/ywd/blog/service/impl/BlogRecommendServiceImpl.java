package com.ywd.blog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ywd.blog.dao.BlogRecommendDao;
import com.ywd.blog.entity.BlogRecommend;
import com.ywd.blog.service.BlogRecommendService;

@Service("blogRecommendService")
public class BlogRecommendServiceImpl implements BlogRecommendService {

	@Autowired
	private BlogRecommendDao blogRecommendDao;
	
	@Override
	public List<BlogRecommend> list(Map<String, Object> map) {
		return blogRecommendDao.list(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return blogRecommendDao.getTotal(map);
	}

	@Override
	public int add(BlogRecommend BlogRecommend) {
		return blogRecommendDao.add(BlogRecommend);
	}

	@Override
	public int update(BlogRecommend BlogRecommend) {
		return blogRecommendDao.update(BlogRecommend);
	}

	@Override
	public int delete(Integer id) {
		return blogRecommendDao.delete(id);
	}

}
