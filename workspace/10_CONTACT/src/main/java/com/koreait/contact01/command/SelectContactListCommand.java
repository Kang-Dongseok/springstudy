package com.koreait.contact01.command;

import org.springframework.ui.Model;

import com.koreait.contact01.dao.ContactDAO;

public class SelectContactListCommand implements ContactCommand {

	@Override
	public void execute(Model model) {
		
		model.addAttribute("list", ContactDAO.getInstance().selectContactList());
		
	}

}
