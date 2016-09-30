package com.ywd.blog.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ywd.blog.entity.Blog;
import com.ywd.blog.entity.Comment;
import com.ywd.blog.entity.Config;
import com.ywd.blog.service.BlogService;
import com.ywd.blog.service.CommentService;
import com.ywd.blog.util.Constant;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Resource
	private BlogService blogService;
	
	@Resource
	private CommentService commentService;
	
	private Random r = new Random(47);
	
	/**
	 * 添加评论
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(Comment comment, @RequestParam("imageCode") String imageCode, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception{
		String sRand=(String) session.getAttribute("sRand");
		JSONObject result=new JSONObject();
		int resultTotal=0;
		if(!imageCode.equals(sRand)){
			result.put("success", false);
			result.put("errorInfo", "验证码填写错误!");
		}else{
			if(comment.getId()==null){
				String userIp=request.getRemoteAddr(); // 获取用户IP
				comment.setUserIp(userIp);
				comment.setType(Constant.BLOG_COMMENT);
				resultTotal=commentService.add(comment);
				// 博客的回复次数加1
				Blog blog=blogService.findById(comment.getBlog().getId());
				blog.setReplyHit(blog.getReplyHit()+1);
				blogService.update(blog);
			}else{
				Map<String, Object> map = new HashMap<>();
				map.put("reComment", comment.getReComment());
				map.put("id", comment.getId());
				resultTotal = commentService.update(map);
			}
		}
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		return result.toString();
	}
	
	/**
	 * 反馈
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveFedback")
	@ResponseBody
	public String saveFedback(Comment comment, HttpServletRequest request) throws Exception{
		JSONObject result=new JSONObject();
		int resultTotal=0;
		if(comment.getId()==null){
			String userIp=request.getRemoteAddr(); // 获取用户IP
			comment.setUserIp(userIp);
			comment.setType(Constant.FEDBACK);
			resultTotal = commentService.add(comment);
		}else{
			Map<String, Object> map = new HashMap<>();
			map.put("reComment", comment.getReComment());
			map.put("id", comment.getId());
			resultTotal = commentService.update(map);
		}
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		return result.toString();
	}
	
	/**
	 * 留言
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveLeaveWord")
	@ResponseBody
	public String saveLeaveWord(Comment comment, HttpServletRequest request, HttpSession session) throws Exception{
		String sRand=(String) session.getAttribute("sRand");
		JSONObject result=new JSONObject();
		int resultTotal=0;
		if(comment.getId()==null){
			String userIp=request.getRemoteAddr(); // 获取用户IP
			comment.setUserIp(userIp);
			comment.setType(Constant.LEAVE_WORD);
			comment.setPicture(r.nextInt(Config.tux)+1+".jpg");
			resultTotal=commentService.add(comment);
		}else{
			Map<String, Object> map = new HashMap<>();
			map.put("reComment", comment.getReComment());
			map.put("id", comment.getId());
			resultTotal = commentService.update(map);
		}
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		return result.toString();
	}
	
	/**
	 * 反馈
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateState")
	@ResponseBody
	public String updateState(Comment comment, HttpServletRequest request) throws Exception{
		JSONObject result=new JSONObject();
		int resultTotal=0;
		Map<String, Object> map = new HashMap<>();
		map.put("state", comment.getState());
		map.put("id", comment.getId());
		resultTotal = commentService.update(map);
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		return result.toString();
	}
	
}
