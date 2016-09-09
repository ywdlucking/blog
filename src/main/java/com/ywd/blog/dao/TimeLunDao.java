package com.ywd.blog.dao;

import java.util.List;
import java.util.Map;

import com.ywd.blog.entity.TimeLun;

public interface TimeLunDao {
	
	public List<TimeLun> list(Map<String, Object> map);
	
	public List<TimeLun> listAllTimes(Map<String, Object> map);
	
	public Long getTotal(Map<String, Object> map);
	
	public TimeLun findById(Integer id);
	
	public List<Integer> group();
	
	public Integer update(TimeLun TimeLun);
	
	public int add(TimeLun TimeLun);
	
	public Integer delete(Integer id);
}
