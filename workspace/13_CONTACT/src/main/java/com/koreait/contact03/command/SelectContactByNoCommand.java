package com.koreait.contact03.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.contact03.dao.ContactDAO;

public class SelectContactByNoCommand implements ContactCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		long no = (Long)map.get("no");
		
		ContactDAO contactDAO = sqlSession.getMapper(ContactDAO.class);
		model.addAttribute("contact", contactDAO.selectContactByNo(no));
	}

}
