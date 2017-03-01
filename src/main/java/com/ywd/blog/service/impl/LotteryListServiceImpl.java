package com.ywd.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ywd.blog.dao.LotteryListDao;
import com.ywd.blog.entity.LotteryList;
import com.ywd.blog.service.LotteryListService;

@Service
public class LotteryListServiceImpl implements LotteryListService {
	
	@Autowired
	private LotteryListDao lotteryListDao;

	@Override
	public Integer add(LotteryList lotteryList) {
		return lotteryListDao.add(lotteryList);
	}

	@Override
	public Integer deleteById(Long id) {
		return lotteryListDao.deleteById(id);
	}

	@Override
	public Integer deleteAll() {
		return lotteryListDao.deleteAll();
	}

	@Override
	public List<LotteryList> findByRank(Integer rank) {
		return lotteryListDao.findByRank(rank);
	}

}
