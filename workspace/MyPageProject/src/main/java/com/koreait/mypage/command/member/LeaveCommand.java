package com.koreait.mypage.command.member;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.mypage.dao.MemberDAO;
import com.koreait.mypage.dto.MemberDTO;

public class LeaveCommand implements MemberCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession)map.get("session");
		
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("loginUser");
		String id = memberDTO.getId();
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		memberDAO.deleteMember(id);
		
		session.invalidate();
		
	}

}
