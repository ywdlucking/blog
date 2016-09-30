package com.ywd.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ywd.blog.entity.Link;
import com.ywd.blog.entity.Scoll;
import com.ywd.blog.service.LinkService;
import com.ywd.blog.service.ScollService;
import com.ywd.blog.util.Constant;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/link")
public class LinkController {
	
	@Autowired
	private LinkService linkService;
	
	/**
	 * 点击轮播页面
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public String index(Link link, HttpServletRequest request) throws Exception{
		JSONObject result=new JSONObject();
		link.setState(Constant.LINK_NO);
		int resultInt = linkService.add(link);
		if(resultInt > 0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		return result.toString();
	}
}
