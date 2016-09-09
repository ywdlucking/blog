package com.ywd.blog.service.impl;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.ywd.blog.entity.Config;
import com.ywd.blog.service.QiNiuService;

@Service("qiNiuService")
public class QiNiuServiceImpl implements QiNiuService {

	private static Log log = LogFactory.getLog(QiNiuServiceImpl.class);

	// 密钥配置
	Auth auth = Auth.create(Config.ACCESS_KEY, Config.SECRET_KEY);
	// 创建上传对象
	UploadManager uploadManager = new UploadManager();

	@Override
	public void upload(File file) {
		try {
			// 调用put方法上传，这里指定的key和上传策略中的key要一致
			Response res = uploadManager.put(file, file.getName(), getUpToken(file.getName()));
			// 打印返回的信息
			System.out.println(res.bodyString());
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常信息
			log.info(r.toString());
			try {
				// 响应的文本信息
				log.info(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
	}

	private String getUpToken(String key) {
		// <bucket>:<key>，表示只允许用户上传指定key的文件。在这种格式下文件默认允许“修改”，已存在同名资源则会被本次覆盖。
		return auth.uploadToken(Config.bucketname, key);
	}

}
