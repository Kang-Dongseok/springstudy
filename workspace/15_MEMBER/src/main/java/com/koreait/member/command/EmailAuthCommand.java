package com.koreait.member.command;

import java.util.HashMap;
import java.util.Map;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;

public class EmailAuthCommand {

	@Autowired
	private JavaMailSender mailSender;
	
	public Map<String, String> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String email = request.getParameter("email");  // 인증번호를 받는 사람 이메일
		
		// MimeMessage 클래스
		// 이메일을 작성하는 클래스
		MimeMessage message = mailSender.createMimeMessage();
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!$?&";
		String authCode = "";
		try {
			message.setHeader("Content-Type", "text/plain; charset=utf-8");
			message.setFrom(new InternetAddress("forspringlec@gmail.com", "관리자"));  // 보내는 사람
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));  // 받는 사람
			message.setSubject("인증 요청 메일입니다.");
			for (int i = 0; i < 6; i ++) {
				authCode += chars.charAt((int)(Math.random() * chars.length()));
			}
			message.setText("인증번호는 " + authCode + "입니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 이메일 보내기
		mailSender.send(message);
		

		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("authCode", authCode);
		return resultMap;
	}

}
