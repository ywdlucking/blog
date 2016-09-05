package com.ywd.blog.service;

import java.util.List;
import java.util.Map;

import com.ywd.blog.entity.TimeLun;

public interface TimeLunService {
	public List<TimeLun> list(Map<String, Object> map);
	
	public Integer update(TimeLun TimeLun);
	
	public int add(TimeLun TimeLun);
	
	public Integer delete(Integer id);
	
	public List<Integer> group();
}
