package com.ywd.blog.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.ywd.blog.entity.TimeLun;
import com.ywd.blog.service.TimeLunService;
import com.ywd.blog.util.DateJsonValueProcessor;
import com.ywd.blog.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


@Controller
@RequestMapping("/admin/time")
public class TimeAdminController {

	@Autowired
	private TimeLunService timeService;
	
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
		List<TimeLun> listTime = timeService.listAll(map);
		Long total = timeService.getTotal(map);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray array = JSONArray.fromObject(listTime, jsonConfig);
		JSONObject result = new JSONObject();
		result.put("rows", array);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/save")
	public String save(TimeLun time, HttpServletResponse response)throws Exception{
		if(!time.getPicture().equals("")){
			String[] split = time.getPicture().split("\\\\");
			if(split.length>1){
				time.setPicture(split[split.length-1]);				
			}
		}
		int result = 0;
		if(time.getId() == null){
			time.setSaydate(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			time.setSyear(Integer.valueOf(sdf.format(new Date())));
			result = timeService.add(time);
		}else{
			result = timeService.update(time);
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
				timeService.delete(Integer.valueOf(id));
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
		TimeLun time = timeService.findById(Integer.parseInt(id));
		JSONObject result=JSONObject.fromObject(time);
		return result.toString();
	}
}
