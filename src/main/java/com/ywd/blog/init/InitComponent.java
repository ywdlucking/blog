package com.ywd.blog.init;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.ywd.blog.entity.Blog;
import com.ywd.blog.entity.BlogType;
import com.ywd.blog.entity.Blogger;
import com.ywd.blog.entity.Link;
import com.ywd.blog.service.BlogService;
import com.ywd.blog.service.BlogTypeService;
import com.ywd.blog.service.BloggerService;
import com.ywd.blog.service.LinkService;

@Component
public class InitComponent implements ServletContextListener, ApplicationContextAware {
	
	private static ApplicationContext applicationContext;

	@SuppressWarnings("static-access")
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void contextInitialized(ServletContextEvent event) {
		try {
			InitConfig.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ServletContext servletContext = event.getServletContext();
		
		BloggerService bloggerService = (BloggerService) applicationContext.getBean("bloggerService");
		Blogger blogger = bloggerService.findBlogger();
		blogger.setPassword(null);
		servletContext.setAttribute("blogger", blogger);
		
		LinkService linkService = (LinkService) applicationContext.getBean("linkService");
		List<Link> links = linkService.list(null);
		servletContext.setAttribute("links", links);
		
		BlogTypeService blogTypeService = (BlogTypeService) applicationContext.getBean("blogTypeService");
		List<BlogType> blogTypes = blogTypeService.countList();
		servletContext.setAttribute("blogTypes", blogTypes);
		
		BlogService blogService = (BlogService) applicationContext.getBean("blogService");
		List<Blog> blogs = blogService.countList();
		servletContext.setAttribute("blogs", blogs);
	}
	
	public void contextDestroyed(ServletContextEvent arg0) {

	}


}
