package com.ywd.blog.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ywd.blog.entity.Blogger;
import com.ywd.blog.service.BloggerService;
import com.ywd.blog.util.CryptographyUtil;


@Controller
@RequestMapping("/blogger")
public class BloggerController {

	@Resource
	private BloggerService bloggerService;
	
	@RequestMapping("/login")
	public String login(Blogger blogger,HttpServletRequest request){
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(blogger.getUserName(), CryptographyUtil.md5(blogger.getPassword(), CryptographyUtil.P_KEY));
		try{
			subject.login(token); 	
			return "redirect:/admin/main.jsp";
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("blogger", blogger);
			request.setAttribute("errorInfo", "用户名密码");
			return "login";
		}
	}
	
	@RequestMapping("/aboutMe")
	public ModelAndView aboutMe(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pageTitle", "关于博主");
		modelAndView.addObject("mainPage", "foreground/blogger/info.jsp");
		modelAndView.setViewName("mainTemp");
		return modelAndView;
	}
	
	@RequestMapping("/download")
	public ModelAndView download(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pageTitle", "下载源码");
		modelAndView.addObject("mainPage", "foreground/system/download.jsp");
		modelAndView.setViewName("mainTemp");
		return modelAndView;
	}
}
