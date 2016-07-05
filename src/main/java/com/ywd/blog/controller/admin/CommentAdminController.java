package com.ywd.blog.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ywd.blog.entity.Comment;
import com.ywd.blog.entity.PageBean;
import com.ywd.blog.service.BlogService;
import com.ywd.blog.service.CommentService;
import com.ywd.blog.util.DateJsonValueProcessor;
import com.ywd.blog.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController {
	
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
	@RequestMapping("/list")
	public String list(@RequestParam("page") String page, @RequestParam("rows") String rows,@RequestParam(value="state",required=false) String state,
			@RequestParam(value="blogId",required=false) String blogId,HttpServletResponse response) throws Exception{
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("state", state);
		if(blogId == ""){
			map.put("blogId", null);			
		}else{
			map.put("blogId", blogId);
		}
		List<Comment> commentList = commentService.list(map);
		Long total = commentService.getTotal(map);
		JSONObject result = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray fromObject = JSONArray.fromObject(commentList, jsonConfig);
		result.put("rows", fromObject);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/pass")
	public String pass(@RequestParam("state") String state, @RequestParam("ids") String ids,
			HttpServletResponse response) throws Exception{
		String[] split = ids.split(",");
		for (String id : split) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("state", state);
			commentService.update(map);
		}
		ResponseUtil.write(response, true);
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("ids") String ids, HttpServletResponse response) throws Exception{
		String[] split = ids.split(",");
		for (String id : split) {
			commentService.delete(Integer.parseInt(id));
		}
		ResponseUtil.write(response, true);
		return null;
	}
}
