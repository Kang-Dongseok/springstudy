package com.koreait.contact02.command;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.contact02.dao.ContactDAO;

public class SelectContactByNoCommand implements ContactCommand {

	@Autowired
	private ContactDAO contactDAO;
	@Override
	public void execute(Model model) {

		Map<String, Object> map = model.asMap();
		long no = (Long)map.get("no");
		model.addAttribute("contact", contactDAO.selectContactByNo(no));
	}

}
