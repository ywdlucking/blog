package com.ywd.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ywd.blog.dao.ScollDao;
import com.ywd.blog.entity.Scoll;
import com.ywd.blog.service.ScollService;

@Service("scollService")
public class ScollServiceImpl implements ScollService{
	
	@Autowired
	private ScollDao scollDao;

	@Override
	public List<Scoll> list(Integer count) {
		return scollDao.list(count);
	}

	@Override
	public Scoll findById(Integer id) {
		return scollDao.findById(id);
	}

	@Override
	public Integer update(Scoll scoll) {
		return scollDao.update(scoll);
	}

	@Override
	public int add(Scoll scoll) {
		return scollDao.add(scoll);
	}

	@Override
	public Integer delete(Integer id) {
		return scollDao.delete(id);
	}

	
}
