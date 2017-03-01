package com.ywd.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ywd.blog.dao.LotteryUserDao;
import com.ywd.blog.entity.LotteryUser;
import com.ywd.blog.service.LotteryUserService;

@Service
public class LotteryUserServiceImpl implements LotteryUserService{
	
	@Autowired
	private LotteryUserDao lotteryUserDao;

	@Override
	public Integer add(LotteryUser lotteryUser) {
		return lotteryUserDao.add(lotteryUser);
	}

	@Override
	public Integer deleteById(Long id) {
		return lotteryUserDao.deleteById(id);
	}

	@Override
	public Integer findById(Long id) {
		return lotteryUserDao.findById(id);
	}

	@Override
	public Long getTotal() {
		return lotteryUserDao.getTotal();
	}

	@Override
	public List<LotteryUser> findAll() {
		return lotteryUserDao.findAll();
	}

}
