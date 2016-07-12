package com.ywd.blog.controller.admin;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ywd.blog.entity.Blog;
import com.ywd.blog.entity.BlogIndex;
import com.ywd.blog.lucene.BlogIndexService;
import com.ywd.blog.service.IndexService;
import com.ywd.blog.util.ResponseUtil;


@Controller
@RequestMapping("/admin/ix")
public class BlogIndexController {
	
	@Autowired
	private IndexService index;
	
	private BlogIndexService blogIndex = new BlogIndexService();
	
	
	@RequestMapping("/update")
	public String update(@RequestParam(value="ids", required=true)String ids, HttpServletResponse response)throws Exception{
		String[] split = ids.split(",");
		try {
			for (String id : split) {
				BlogIndex bi = index.findByBlogId(Integer.valueOf(id));
				Blog blog = new Blog();
				blog.setId(bi.getBlogId());
				blog.setTitle(bi.getBlogTitle());
				blog.setNoTagContent(bi.getNoTagContent());
				blogIndex.updateIndex(blog);
			}
		} catch (Exception e) {
			ResponseUtil.write(response, false);
		}
		ResponseUtil.write(response, true);
		return null;
	}
}
