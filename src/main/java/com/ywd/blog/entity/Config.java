package com.ywd.blog.entity;

import java.io.IOException;
import java.util.Properties;

public class Config {
	
	public static String blog_lucene_path = null;
	public static String ACCESS_KEY = null;
	public static String SECRET_KEY = null;
	public static String bucketname = null;
	
	static{
		Properties p = new Properties();
		try {
			p.load(Config.class.getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		blog_lucene_path = p.getProperty("blog_lucene_path");
		ACCESS_KEY = p.getProperty("ACCESS_KEY");
		SECRET_KEY = p.getProperty("SECRET_KEY");
		bucketname = p.getProperty("bucketname");
	}
}
