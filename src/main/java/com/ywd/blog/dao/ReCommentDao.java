package com.ywd.blog.dao;

import com.ywd.blog.entity.Blog;

public interface ReCommentDao {
	
	/**
	 * 通过id查找评论回复
	 * @param id
	 * @return
	 */
	public Blog findById(Integer id);
}
