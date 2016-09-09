package com.ywd.blog.init;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.ywd.blog.entity.Config;


public class InitConfig {
	
	
	public void init() throws IOException{
		System.out.println(Config.blog_lucene_path);
	}
}
