package com.koreait.mypage.command.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.mypage.dao.MemberDAO;
import com.koreait.mypage.dto.MemberDTO;

public class LoginCommand implements MemberCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(id);
		memberDTO.setPw(pw);
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		MemberDTO loginUser = memberDAO.selectOneMember(memberDTO);
		
		HttpSession session = request.getSession();
		if (loginUser != null) {
			session.setAttribute("loginUser", loginUser);
		}
	}

}
