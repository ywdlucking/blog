package com.ywd.blog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ywd.blog.dao.TimeLunDao;
import com.ywd.blog.entity.TimeLun;
import com.ywd.blog.service.TimeLunService;

@Service("timeLunService")
public class TimeLunServiceImpl implements TimeLunService{
	
	@Autowired
	private TimeLunDao timeLunDao;

	@Override
	public List<TimeLun> list(Map<String, Object> map) {
		return timeLunDao.list(map);
	}

	@Override
	public Integer update(TimeLun TimeLun) {
		return timeLunDao.update(TimeLun);
	}

	@Override
	public int add(TimeLun TimeLun) {
		return timeLunDao.add(TimeLun);
	}

	@Override
	public Integer delete(Integer id) {
		return timeLunDao.delete(id);
	}

	@Override
	public List<Integer> group() {
		return timeLunDao.group();
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return timeLunDao.getTotal(map);
	}

	@Override
	public TimeLun findById(Integer id) {
		return timeLunDao.findById(id);
	}

	@Override
	public List<TimeLun> listAll(Map<String, Object> map) {
		return timeLunDao.listAllTimes(map);
	}

}
