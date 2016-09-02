package com.ywd.blog.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.ywd.blog.entity.Blog;
import com.ywd.blog.entity.PageBean;
import com.ywd.blog.entity.Scoll;
import com.ywd.blog.entity.TimeLun;
import com.ywd.blog.service.BlogService;
import com.ywd.blog.service.ScollService;
import com.ywd.blog.service.TimeLunService;
import com.ywd.blog.util.Constant;
import com.ywd.blog.util.PageUtil;
import com.ywd.blog.util.StringUtil;
import com.ywd.blog.vo.ScollVO;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private ScollService scollService;
	
	@Autowired
	private TimeLunService timeLunService;
	
	/**
	 * 请求主页
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView();
		List<ScollVO> scollVO = new ArrayList<ScollVO>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", 0);
		map.put("size", 7);
		List<Blog> listBlog = blogService.list(map);
		List<Scoll> scolls = scollService.list(4);
		for (Scoll scoll : scolls) {
			ScollVO vo = new ScollVO("slide-img-"+scoll.getTitleId(), scoll.getClient(), scoll.getDesc());
			scollVO.add(vo);
		}
		modelAndView.addObject("listBlog", listBlog);
		modelAndView.addObject("scolls", scolls);
		modelAndView.addObject("scollVO", new Gson().toJson(scollVO));
		modelAndView.addObject("pageTitle", "博客系统");
		modelAndView.addObject("mainPage", "foreground/blog/list-recommend.jsp");
		modelAndView.setViewName("mainTemp");
		return modelAndView;
	}
	
	
	/**
	 * 请求博客列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/article")
	public ModelAndView article(@RequestParam(value="page", required=false)String page,@RequestParam(value="typeId", required=false)String typeId,@RequestParam(value="releaseDateStr", required=false)String releaseDateStr, HttpServletRequest request) throws Exception{
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
	
	/**
	 * 请求时间轮
	 * @return
	 */
	@RequestMapping("/time")
	public ModelAndView time(@RequestParam(value="syear", required=false)String syear, HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(syear)){
			map.put("syear", Integer.valueOf(syear));
		}
		List<TimeLun> list = timeLunService.list(map);
		List<Integer> group = timeLunService.group();
		String tiemPage = PageUtil.getTiemPage(request.getContextPath(), group, syear==null?null:Integer.valueOf(syear));
		modelAndView.addObject("listTime", list);
		modelAndView.addObject("pageCode", tiemPage);
		modelAndView.addObject("pageTitle", "博客系统");
		modelAndView.addObject("mainPage", "foreground/blog/time.jsp");
		modelAndView.setViewName("mainTemp");
		return modelAndView;
	}
	
	@RequestMapping("/map")
	public ModelAndView map( HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pageTitle", "博客系统");
		modelAndView.addObject("mainPage", "foreground/blog/map.jsp");
		modelAndView.setViewName("mainTemp");
		return modelAndView;
	}
}
