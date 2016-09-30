package com.ywd.blog.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ywd.blog.dao.CommentDao;
import com.ywd.blog.entity.Comment;
import com.ywd.blog.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	
	@Resource
	private CommentDao commentDao;

	public List<Comment> list(Map<String, Object> map) {
		return commentDao.list(map);
	}

	public int add(Comment comment) {
		return commentDao.add(comment);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return commentDao.getTotal(map);
	}

	@Override
	public int update(Map<String, Object> map) {
		return commentDao.update(map);
	}

	@Override
	public int delete(Integer id) {
		return commentDao.delete(id);
	}

	@Override
	public Comment findById(Integer id) {
		return commentDao.findById(id);
	}

}
