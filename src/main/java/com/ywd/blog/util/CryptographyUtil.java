package com.ywd.blog.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class CryptographyUtil {
	
	public static final String P_KEY = "ywd";

	public static String md5(String str,String salt){
		return new Md5Hash(str,salt).toString();
	}
	
	public static void main(String[] args) {
		
		System.out.println("Md5:"+CryptographyUtil.md5("123456", P_KEY));
	}
}
