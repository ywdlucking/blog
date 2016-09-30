package com.ywd.blog.service.impl;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.ywd.blog.service.EmailService;

@Service("emailService")
public class EmailServiceImpl implements EmailService {
	
	private static String HOST = "smtp.sina.com";  
	private static String PROTOCOL = "smtp";     
	private static int PORT = 25;  
	private static String FROM = "master_2016@sina.com";//发件人的email  
	private static String PWD = "master0728";//发件人密码
	private static Session session = null;
    
	public boolean sendEmail(String receiveAdress, String message, String subject) throws Exception{
		Session session = getSession();  
        Message msg = new MimeMessage(session);     
        msg.setFrom(new InternetAddress(FROM));  
        InternetAddress[] address = {new InternetAddress(receiveAdress)};  
        msg.setRecipients(Message.RecipientType.TO, address);  
        msg.setSubject(subject);  
        msg.setSentDate(new Date());  
        msg.setContent(message , "text/html;charset=utf-8");     
        Transport.send(msg);  
		return true;
	} 
	
	
	/** 
     * 获取Session 
     * @return 
     */  
    private synchronized Session getSession() {  
    	if(session == null){
    		Properties props = new Properties();  
    		props.put("mail.smtp.host", HOST);//设置服务器地址  
    		props.put("mail.store.protocol" , PROTOCOL);//设置协议  
    		props.put("mail.smtp.port", PORT);//设置端口  
    		props.put("mail.smtp.auth" , true);  
    		Authenticator authenticator = new Authenticator() {  
    			@Override  
    			protected PasswordAuthentication getPasswordAuthentication() {  
    				return new PasswordAuthentication(FROM, PWD);  
    			}  
    		};  
    		session = Session.getDefaultInstance(props , authenticator);  
    	}
    	return session;      		
    }  
	
	
}
