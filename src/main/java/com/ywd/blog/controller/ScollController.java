package com.ywd.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ywd.blog.entity.Scoll;
import com.ywd.blog.service.ScollService;

@Controller
@RequestMapping("/blog")
public class ScollController {
	
	private static Log log = LogFactory.getLog(ScollController.class);
	
	@Autowired
	private ScollService scollService;
	
	/**
	 * 点击轮播页面
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/scoll/{id}",method=RequestMethod.GET)
	public ModelAndView index(@PathVariable("id") Integer id, HttpServletRequest request) throws Exception{
		log.info("请求页面："+id);
		ModelAndView modelAndView = new ModelAndView();
		Scoll scoll = scollService.findById(id);
		modelAndView.addObject("scoll", scoll);
		modelAndView.addObject("pageTitle",  scoll.getClient()+"--博客");
		modelAndView.addObject("mainPage", "foreground/blog/view-scoll.jsp");
		modelAndView.setViewName("mainTemp");
		return modelAndView;
	}
}
