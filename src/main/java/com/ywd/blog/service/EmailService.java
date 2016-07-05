package com.ywd.blog.service;

public interface EmailService {
	
	public boolean sendEmail(String receiveAdress, String message, String subject) throws Exception;
}
