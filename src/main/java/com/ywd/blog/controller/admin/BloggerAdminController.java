package com.ywd.blog.controller.admin;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ywd.blog.entity.Blogger;
import com.ywd.blog.service.BloggerService;
import com.ywd.blog.util.CryptographyUtil;
import com.ywd.blog.util.DateUtil;
import com.ywd.blog.util.ResponseUtil;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("/admin/blogger")
public class BloggerAdminController {

	@Resource
	private BloggerService bloggerService;
	
	@RequestMapping("/save")
	public String save(@RequestParam("imageFile") MultipartFile imageFile, Blogger blogger, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(!imageFile.isEmpty()){
			String filePath=request.getServletContext().getRealPath("/");
			String imageName=DateUtil.getCurrentDateStr()+"."+imageFile.getOriginalFilename().split("\\.")[1];
			imageFile.transferTo(new File(filePath+"static/userImages/"+imageName));
			blogger.setImageName(imageName);
		}
		int resultTotal=bloggerService.update(blogger);
		StringBuffer result=new StringBuffer();
		if(resultTotal>0){
			result.append("<script language='javascript'>alert('修改成功！');</script>");
		}else{
			result.append("<script language='javascript'>alert('修改失败！');</script>");
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/find")
	public String find(HttpServletResponse response) throws Exception {
		Blogger blogger = bloggerService.findBlogger();
		JSONObject object = new JSONObject();
		object.put("profile", blogger.getProfile());
		ResponseUtil.write(response, object);
		return null;
	}
	
	@RequestMapping("/updatePassword")
	public String updatePassword(@RequestParam("newPassword")String password, HttpServletResponse response) throws Exception {
		Blogger blogger = bloggerService.findBlogger();
		String md5password = CryptographyUtil.md5(password, CryptographyUtil.P_KEY);
		blogger.setPassword(md5password);
		int update = bloggerService.update(blogger);
		if(update>0){
			ResponseUtil.write(response, true);			
		}else{
			ResponseUtil.write(response, false);
		}
		return null;
	}
	
	@RequestMapping("/logout")
	public String logout() throws Exception {
		SecurityUtils.getSubject().logout();
		return "redirect:/login.jsp";
	}
}
