package com.ywd.blog.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ywd.blog.entity.Blog;
import com.ywd.blog.entity.Comment;
import com.ywd.blog.service.BlogService;
import com.ywd.blog.service.CommentService;
import com.ywd.blog.util.ResponseUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Resource
	private BlogService blogService;
	
	@Resource
	private CommentService commentService;
	
	/**
	 * 添加评论
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(Comment comment, @RequestParam("imageCode") String imageCode, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception{
		String sRand=(String) session.getAttribute("sRand");
		JSONObject result=new JSONObject();
		int resultTotal=0;
		if(!imageCode.equals(sRand)){
			result.put("success", false);
			result.put("errorInfo", "验证码填写错误!");
		}else{
			String userIp=request.getRemoteAddr(); // 获取用户IP
			comment.setUserIp(userIp);
			if(comment.getId()==null){
				resultTotal=commentService.add(comment);
				// 博客的回复次数加1
				Blog blog=blogService.findById(comment.getBlog().getId());
				blog.setReplyHit(blog.getReplyHit()+1);
				blogService.update(blog);
			}else{
				
			}
		}
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	
}
