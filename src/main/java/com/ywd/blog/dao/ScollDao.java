package com.ywd.blog.dao;

import java.util.List;

import com.ywd.blog.entity.Scoll;

public interface ScollDao {
	
	public List<Scoll> list(Integer count);
	
	public Scoll findById(Integer id);
	
	public Integer update(Scoll scoll);
	
	public int add(Scoll scoll);
	
	public Integer delete(Integer id);
}
