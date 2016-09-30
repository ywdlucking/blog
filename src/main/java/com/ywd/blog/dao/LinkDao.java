package com.ywd.blog.dao;

import java.util.List;
import java.util.Map;

import com.ywd.blog.entity.Link;

public interface LinkDao {

	public List<Link> list(Map<String,Object> map);
	
	public Long getTotal(Map<String,Object> map);
	
	public int add(Link link);
	
	public int update(Link link);
	
	public int delete(Integer id);
	
	public Link findById(Integer id);
}
