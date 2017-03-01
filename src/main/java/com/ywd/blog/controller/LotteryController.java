package com.ywd.blog.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ywd.blog.entity.LotteryList;
import com.ywd.blog.entity.LotteryUser;
import com.ywd.blog.service.LotteryListService;
import com.ywd.blog.service.LotteryUserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/lottery")
public class LotteryController {

	@Autowired
	private LotteryUserService lotteryUserService;

	@Autowired
	private LotteryListService lotteryListService;

	private Gson gson = new Gson();

	@RequestMapping(value = "/findAllUser", method = RequestMethod.GET)
	@ResponseBody
	public String findAllUser() throws Exception {
		List<LotteryUser> findAll = lotteryUserService.findAll();
		return gson.toJson(findAll);
	}
	
	@RequestMapping(value = "/findAllLotteryUser", method = RequestMethod.GET)
	@ResponseBody
	public String findAllLotteryUser() throws Exception {
		List<LotteryList> l0 = lotteryListService.findByRank(0);
		List<LotteryList> l1 = lotteryListService.findByRank(1);
		List<LotteryList> l2 = lotteryListService.findByRank(2);
		List<LotteryList> l3 = lotteryListService.findByRank(3);
		Map<String, List<LotteryList>> map = new HashMap<>();
		map.put("te", l0);
		map.put("yi", l1);
		map.put("er", l2);
		map.put("san", l3);
		return gson.toJson(map);
	}

	@RequestMapping(value = "/findById", method = RequestMethod.GET)
	@ResponseBody
	public String findById(@RequestParam("id") String id) throws Exception {
		Integer findById = lotteryUserService.findById(Long.valueOf(id));
		return gson.toJson(findById);
	}

	@RequestMapping(value = "/findByRank", method = RequestMethod.GET)
	@ResponseBody
	public String findByRank(@RequestParam("rank") String rank) throws Exception {
		List<LotteryList> findByRank = lotteryListService.findByRank(Integer.valueOf(rank));
		return gson.toJson(findByRank);
	}

	@RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
	@ResponseBody
	public String deleteAll(@RequestParam(value = "rank", required = false) String rank) throws Exception {
		if(rank == null || rank.equals("")){
			lotteryListService.deleteAll();
		}else{
			List<LotteryList> findByRank = lotteryListService.findByRank(Integer.valueOf(rank));
			for (LotteryList lotteryList : findByRank) {
				lotteryListService.deleteById(lotteryList.getId());
			}
		}
		return "true";
	}
	
	@RequestMapping(value = "/saveLotteryUser", method = RequestMethod.POST)
	@ResponseBody
	public String saveLotteryUser(@RequestParam(value = "level") String level, @RequestParam(value = "info") String info) throws Exception {
		Integer rank = Integer.valueOf(level);
		JSONArray jsonArray = JSONArray.fromObject(info);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			LotteryList lotteryList = new LotteryList();
			lotteryList.setRank(rank);
			lotteryList.setUserName(object.getString("name"));
			lotteryList.setUserNo(object.getString("no"));
			lotteryList.setCdate(new Date());
			lotteryListService.add(lotteryList);
		}
		return "success";
	}
}
