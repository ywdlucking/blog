package com.ywd.blog.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ywd.blog.entity.Blog;
import com.ywd.blog.entity.BlogIndex;
import com.ywd.blog.entity.PageBean;
import com.ywd.blog.lucene.BlogIndexService;
import com.ywd.blog.service.BlogService;
import com.ywd.blog.service.IndexService;
import com.ywd.blog.util.DateJsonValueProcessor;
import com.ywd.blog.util.ResponseUtil;
import com.ywd.blog.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {
	
	@Resource
	private BlogService blogService;
	
	@Autowired
	private IndexService indexService;
	
	private BlogIndexService blogIndex = new BlogIndexService();
	
	/**
	 * 添加或者修改博客信息
	 * @param blog
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(Blog blog,HttpServletResponse response) throws Exception{
		int resultTotal = 0; 
		if(blog.getId()==null){
			resultTotal=blogService.add(blog);
			blogIndex.addIndex(blog);
			BlogIndex blogIndex = new BlogIndex(blog.getId(), blog.getTitle(), blog.getNoTagContent());
			indexService.add(blogIndex);
		}else{
			resultTotal = blogService.update(blog);
			blogIndex.updateIndex(blog);
			BlogIndex blogIndex = new BlogIndex(blog.getId(), blog.getTitle(), blog.getNoTagContent());
			indexService.update(blogIndex);
		}
		JSONObject result=new JSONObject();
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 分页查询博客信息
	 * @param page
	 * @param rows
	 * @param blog
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page, @RequestParam(value="rows",required=false)String rows, Blog blog,HttpServletResponse response) throws Exception{
		JSONObject result=new JSONObject();
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("title", StringUtil.formatLike(blog.getTitle()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Blog> blogList = blogService.list(map);
		Long total = blogService.getTotal(map);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray = JSONArray.fromObject(blogList, jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 博客信息删除
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String deleteBlog(@RequestParam(value="ids",required=true)String ids,HttpServletResponse response) throws Exception{
		String[] idStr = ids.split(",");
		for (int i = 0; i < idStr.length; i++) {
			blogService.deleteBlog(Integer.parseInt(idStr[i]));
			blogIndex.delete(idStr[i]);
			indexService.delete(Integer.parseInt(idStr[i]));
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 通过Id查找实体
	 * @param id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/modifyByBlogId")
	public String modifyByBlogId(@RequestParam(value="id",required=true)String id,HttpServletResponse response) throws Exception{
		Blog blog = blogService.findById(Integer.parseInt(id));
		JSONObject result=JSONObject.fromObject(blog);
		ResponseUtil.write(response, result);
		return null;
	}
	
}
