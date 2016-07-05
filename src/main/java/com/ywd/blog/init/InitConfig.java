package com.ywd.blog.init;

import java.io.IOException;

import com.ywd.blog.entity.Config;

public class InitConfig {
	
	public static void init() throws IOException{
		
		System.out.println(Config.blog_lucene_path);
	}
}
