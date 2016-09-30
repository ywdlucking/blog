package com.ywd.blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ywd.blog.dao.LinkDao;
import com.ywd.blog.entity.Link;
import com.ywd.blog.service.LinkService;

@Service("linkService")
public class LinkServiceImpl implements LinkService{

	@Resource
	private LinkDao linkDao;
	
	public List<Link> list(Map<String, Object> map) {
		return linkDao.list(map);
	}

	@Override
	public int add(Link link) {
		return linkDao.add(link);
	}

	@Override
	public int update(Link link) {
		return linkDao.update(link);
	}

	@Override
	public int delete(Integer id) {
		return linkDao.delete(id);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return linkDao.getTotal(map);
	}

	@Override
	public Link findById(Integer id) {
		return linkDao.findById(id);
	}

}
