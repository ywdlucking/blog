package com.ywd.blog.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.ywd.blog.entity.BlogRecommend;
import com.ywd.blog.entity.Comment;
import com.ywd.blog.entity.PageBean;
import com.ywd.blog.entity.Scoll;
import com.ywd.blog.entity.TimeLun;
import com.ywd.blog.service.BlogRecommendService;
import com.ywd.blog.service.BlogService;
import com.ywd.blog.service.CommentService;
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
	
	@Autowired
	private BlogRecommendService blogRecommendService;
	
	@Autowired
	private CommentService commentService;
	
	/**
	 * 请求主页
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request){
		HttpSession session = request.getSession();
		ModelAndView modelAndView = new ModelAndView();
		List<ScollVO> scollVO = new ArrayList<ScollVO>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", 0);
		map.put("size", 6);
		Long total = blogRecommendService.getTotal(map);
		List<Blog> listBlogTop = blogService.listRecommend(map);
		List<Blog> listBlogNew = blogService.listNew(map);
		List<Blog> listBlogRecommend = new ArrayList<>();
		if(total<6){
			listBlogRecommend = listBlogTop;
		}else{
			List<BlogRecommend> list = blogRecommendService.list(map);
			for (BlogRecommend blogRecommend : list) {
				listBlogRecommend.add(blogService.findById(blogRecommend.getRecommendId()));
			}
		}
		Map<String, Object> mapScoll = new HashMap<String, Object>();
		mapScoll.put("count", 4);
		List<Scoll> scolls = scollService.list(mapScoll);
		for (Scoll scoll : scolls) {
			ScollVO vo = new ScollVO("slide-img-"+scoll.getTitleId(), scoll.getClient(), scoll.getDesc());
			scollVO.add(vo);
		}
		session.setAttribute("listBlogRecommend", listBlogRecommend);
		session.setAttribute("listBlogNew", listBlogNew);
		session.setAttribute("listBlogTop", listBlogTop);
//		modelAndView.addObject("listBlogRecommend", listBlogRecommend);
//		modelAndView.addObject("listBlogNew", listBlogNew);
//		modelAndView.addObject("listBlogTop", listBlogTop);
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
		modelAndView.addObject("pageTitle", "时间轴");
		modelAndView.addObject("mainPage", "foreground/system/time.jsp");
		modelAndView.setViewName("mainTemp");
		return modelAndView;
	}
	
	@RequestMapping("/map")
	public ModelAndView map( HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pageTitle", "我的地图");
		modelAndView.addObject("mainPage", "foreground/system/map.jsp");
		modelAndView.setViewName("mainTemp");
		return modelAndView;
	}
	
	@RequestMapping("/message")
	public ModelAndView message(@RequestParam(value="page", required=false)String page, HttpServletRequest request){
		if(StringUtil.isEmpty(page)){
			page = "1";
		}
		int currentPage = Integer.parseInt(page);
		PageBean pageBean = new PageBean(currentPage, Constant.PAGE_SIZE);
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("type", Constant.LEAVE_WORD);
		map.put("state", Constant.COMMENT_STATE_PASS);
		List<Comment> messages = commentService.list(map);
		StringBuffer parm = new StringBuffer();
		parm.append("state=3");
		modelAndView.addObject("messages", messages);
		modelAndView.addObject("pageCode", PageUtil.genPagination(request.getContextPath(), commentService.getTotal(map), currentPage, 10, parm.toString()));
		modelAndView.addObject("pageTitle", "留言板");
		modelAndView.addObject("mainPage", "foreground/system/message.jsp");
		modelAndView.setViewName("mainTemp");
		return modelAndView;
	}
}
