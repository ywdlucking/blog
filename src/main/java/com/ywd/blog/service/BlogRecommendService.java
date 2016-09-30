package com.ywd.blog.service;

import java.util.List;
import java.util.Map;

import com.ywd.blog.entity.BlogRecommend;

public interface BlogRecommendService {
	
	public List<BlogRecommend> list(Map<String,Object> map);
	
	public Long getTotal(Map<String,Object> map);
	
	public int add(BlogRecommend BlogRecommend);
	
	public int update(BlogRecommend BlogRecommend);
	
	public int delete(Integer id);
}
