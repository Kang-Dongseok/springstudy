package com.koreait.mypage.command.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.mypage.dao.MemberDAO;
import com.koreait.mypage.dto.MemberDTO;

public class JoinCommand implements MemberCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		try {
			request.setCharacterEncoding("utf-8");
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String tel = request.getParameter("tel");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			
			MemberDTO memberDTO = new MemberDTO();
			memberDTO.setId(id);
			memberDTO.setPw(pw);
			memberDTO.setName(name);
			memberDTO.setTel(tel);
			memberDTO.setEmail(email);
			memberDTO.setAddress(address);
			
			MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
			memberDAO.insertMember(memberDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
