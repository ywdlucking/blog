package com.ywd.blog.service;

import java.util.List;
import java.util.Map;

import com.ywd.blog.entity.Scoll;

public interface ScollService {

	public List<Scoll> list(Map<String, Object> map);
	
	public Long getTotal(Map<String,Object> map);
	
	public Scoll findById(Integer id);
	
	public Integer update(Scoll scoll);
	
	public int add(Scoll scoll);
	
	public Integer delete(Integer id);
}
