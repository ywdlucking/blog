package com.ywd.blog.dao;

import java.util.List;

import com.ywd.blog.entity.LotteryList;

public interface LotteryListDao {

	public Integer add(LotteryList lotteryList);
	
	public Integer deleteById(Long id);
	
	public Integer deleteAll();
	
	public List<LotteryList> findByRank(Integer rank);
}
