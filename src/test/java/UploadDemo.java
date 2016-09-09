import java.io.File;
import java.io.IOException;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class UploadDemo {
	// 设置好账号的ACCESS_KEY和SECRET_KEY
	String ACCESS_KEY = "BQAGKCupnGAIV9ivg5H02xqM4AV3yIVJo2f7wePO";
	String SECRET_KEY = "mXPrmPDtpQ_0MGbdk1EWaKxXMJI2beslaeZqMUvx";
	// 要上传的空间
	String bucketname = "blog";
	// 上传到七牛后保存的文件名
	File f = new File("d:/cj1txt.txt");

	// 密钥配置
	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	// 创建上传对象
	UploadManager uploadManager = new UploadManager();

	// 覆盖上传
	public String getUpToken() {
		// <bucket>:<key>，表示只允许用户上传指定key的文件。在这种格式下文件默认允许“修改”，已存在同名资源则会被本次覆盖。
		System.out.println(auth.uploadToken(bucketname));
		return auth.uploadToken(bucketname);
	}

	public void upload() throws IOException {
		
		try {
			// 调用put方法上传，这里指定的key和上传策略中的key要一致
			Response res = uploadManager.put(f, f.getName(), getUpToken());
			// 打印返回的信息
			System.out.println(res.bodyString());
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常信息
			System.out.println(r.toString());
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
	}

	public static void main(String args[]) throws IOException {
		new UploadDemo().upload();
	}

}
