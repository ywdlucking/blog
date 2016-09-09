package com.ywd.blog.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ywd.blog.entity.PageBean;
import com.ywd.blog.entity.Scoll;
import com.ywd.blog.service.ScollService;
import com.ywd.blog.util.DateJsonValueProcessor;
import com.ywd.blog.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


@Controller
@RequestMapping("/admin/scoll")
public class ScollAdminController {
	
	@Autowired
	private ScollService scollService;
	
	/**
	 * 获取所有的博客类别
	 * @param id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=true)String page, @RequestParam(value="rows",required=true)String rows,HttpServletResponse response) throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Scoll> listScoll = scollService.list(map);
		Long total = scollService.getTotal(map);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray array = JSONArray.fromObject(listScoll, jsonConfig);
		JSONObject result = new JSONObject();
		result.put("rows", array);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/save")
	public String save(Scoll scoll, HttpServletResponse response)throws Exception{
		if(!scoll.getPicture().equals("")){
			String[] split = scoll.getPicture().split("\\\\");
			if(split.length>1){
				scoll.setPicture(split[split.length-1]);				
			}
		}
		int result = 0;
		if(scoll.getId() == null){
			result = scollService.add(scoll);
		}else{
			result = scollService.update(scoll);
		}
		if(result > 0){
			ResponseUtil.write(response, true);
		}else{
			ResponseUtil.write(response, false);
		}
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids", required=true)String ids, HttpServletResponse response)throws Exception{
		String[] split = ids.split(",");
		try {
			for (String id : split) {
				scollService.delete(Integer.valueOf(id));
			}
		} catch (Exception e) {
			ResponseUtil.write(response, false);
		}
		ResponseUtil.write(response, true);
		return null;
	}
	
	@RequestMapping(value="/modifyByBlogId",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String modifyByBlogId(@RequestParam(value="id",required=true)String id,HttpServletResponse response) throws Exception{
		Scoll scoll = scollService.findById(Integer.parseInt(id));
		JSONObject result=JSONObject.fromObject(scoll);
		return result.toString();
	}
}
