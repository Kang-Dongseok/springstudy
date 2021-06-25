package com.koreait.member.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class SecurityUtils {

	// 크로스 사이트 스크립트(XSS)
	// 스크립트 입력을 무력화
	public static String xxs(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		return str;
	}
	
	public static String sha256(String str) {
		// java : java.security
		// MessageDigest
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// md.digest() : 암호화 된 바이트 배열
		StringBuilder sb = new StringBuilder();
		for (byte b : md.digest()) {
			sb.append(String.format("%02x", b));  // 16진수 표기법 변환
		}
		return sb.toString();
	}

	// 암호화: "1111" -> "MTExMQ=="
	public static String encodeBase64(String str) {
		// commons-codec
		// Base64
		// Base64.encodeBase64(pw.getBytes()) : 암호화된 바이트 배열
		return new String(Base64.encodeBase64(str.getBytes()));
	}
	
	// 복호화: "MTExMQ==" -> "1111"
	public static String decodeBase64(String str) {
		return new String(Base64.decodeBase64(str.getBytes()));
	}
	
	// 인증코드 생성
	public static String getAuthCode(int length) {
		String authCode = "";
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!$?&";
		for (int i = 0; i < length; i ++) {
			authCode += chars.charAt((int)(Math.random() * chars.length()));
		}
		return authCode;
	}
	
}
