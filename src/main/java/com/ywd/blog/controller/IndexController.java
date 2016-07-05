package com.ywd.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ywd.blog.entity.Blog;
import com.ywd.blog.entity.PageBean;
import com.ywd.blog.service.BlogService;
import com.ywd.blog.util.Constant;
import com.ywd.blog.util.PageUtil;
import com.ywd.blog.util.StringUtil;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@Resource
	private BlogService blogService;
	
	/**
	 * 请求主页
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public ModelAndView index(@RequestParam(value="page", required=false)String page,@RequestParam(value="typeId", required=false)String typeId,@RequestParam(value="releaseDateStr", required=false)String releaseDateStr, HttpServletRequest request) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		if(StringUtil.isEmpty(page)){
			page = "1";
		}
		int currentPage = Integer.parseInt(page);
		PageBean pageBean = new PageBean(currentPage, Constant.PAGE_SIZE);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("typeId", typeId);
		map.put("releaseDateStr", releaseDateStr);
		List<Blog> listBlog = blogService.list(map);
		for (Blog blog : listBlog) {
			List<String> imageList = blog.getImages();
			String content = blog.getContent();
			Document document = Jsoup.parse(content);
			Elements elements = document.select("img[src$=.jpg]");
			for(int i=0;i<elements.size();i++){
				Element element = elements.get(i);
				imageList.add(element.toString());
				if(i==2){
					break;
				}
			}
		}
		StringBuffer parm = new StringBuffer();
		if(StringUtil.isNotEmpty(typeId)){
			parm.append("typeId="+typeId+"&");
		}
		if(StringUtil.isNotEmpty(releaseDateStr)){
			parm.append("releaseDateStr="+releaseDateStr+"&");
		}
		parm.append("");
		modelAndView.addObject("listBlog", listBlog);
		modelAndView.addObject("pageCode", PageUtil.genPagination(request.getContextPath(), blogService.getTotal(map), currentPage, 10, parm.toString()));
		modelAndView.addObject("pageTitle", "博客系统");
		modelAndView.addObject("mainPage", "foreground/blog/list.jsp");
		modelAndView.setViewName("mainTemp");
		return modelAndView;
	}
}
