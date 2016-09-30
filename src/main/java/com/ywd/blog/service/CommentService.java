package com.ywd.blog.service;

import java.util.List;
import java.util.Map;

import com.ywd.blog.entity.Comment;

public interface CommentService {

	/**
	 * 查询用户评论信息
	 * @param map
	 * @return
	 */
	public List<Comment> list(Map<String,Object> map);
	
	/**
	 * 添加评论
	 * @param comment
	 * @return
	 */
	public int add(Comment comment);
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String, Object> map);
	
	/**
	 * 更新数据
	 * @param map
	 * @return
	 */
	public int update(Map<String, Object> map);
	
	/**
	 * 删除评论
	 * @param map
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * 通过id找
	 * @param map
	 * @return
	 */
	public Comment findById(Integer id);
}
