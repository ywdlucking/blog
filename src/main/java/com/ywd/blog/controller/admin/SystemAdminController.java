package com.ywd.blog.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.ywd.blog.entity.Blog;
import com.ywd.blog.entity.BlogType;
import com.ywd.blog.entity.Blogger;
import com.ywd.blog.entity.Link;
import com.ywd.blog.service.BlogService;
import com.ywd.blog.service.BlogTypeService;
import com.ywd.blog.service.BloggerService;
import com.ywd.blog.service.LinkService;
import com.ywd.blog.util.Constant;
import com.ywd.blog.util.ResponseUtil;

@Controller
@RequestMapping("/admin/system")
public class SystemAdminController {

	@Resource
	private BloggerService bloggerService;
	
	@Resource
	private LinkService linkService;
	
	@Resource
	private BlogTypeService blogTypeService;
	
	@Resource
	private BlogService blogService;
	
	@RequestMapping("/refreshSystem")
	public String refreshSystem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ServletContext servletContext = RequestContextUtils.getWebApplicationContext(request).getServletContext();
		Blogger blogger=bloggerService.findBlogger(); // 获取博主信息
		blogger.setPassword(null);
		servletContext.setAttribute("blogger", blogger);
		
		Map<String, Object> map = new HashMap<>();
		map.put("state", Constant.LINK_TRUE);
		List<Link> linkList=linkService.list(map); // 查询所有的友情链接信息
		servletContext.setAttribute("links", linkList);
		
		List<BlogType> blogTypeCountList=blogTypeService.countList(); // 查询博客类别以及博客的数量
		servletContext.setAttribute("blogTypes", blogTypeCountList);
		
		List<Blog> blogCountList=blogService.countList(); // 根据日期分组查询博客
		servletContext.setAttribute("blogs", blogCountList);
		
		ResponseUtil.write(response, true);
		return null;
	}
}
