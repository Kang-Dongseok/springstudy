package com.koreait.contact01.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.contact01.command.DeleteContactCommand;
import com.koreait.contact01.command.InsertContactCommand;
import com.koreait.contact01.command.SelectContactByNoCommand;
import com.koreait.contact01.command.SelectContactListCommand;
import com.koreait.contact01.command.UpdateContactCommand;

@Controller
public class ContactController {

	private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
	private SelectContactListCommand selectContactListCommand;
	private SelectContactByNoCommand selectContactByNoCommand;
	private InsertContactCommand insertContactCommand;
	private UpdateContactCommand updateContactCommand;
	private DeleteContactCommand deleteContactCommand;
	
	@Autowired
	public void setCommand(SelectContactListCommand selectContactListCommand,
						   SelectContactByNoCommand selectContactByNoCommand,
						   InsertContactCommand insertContactCommand,
						   UpdateContactCommand updateContactCommand,
						   DeleteContactCommand deleteContactCommand) {
		this.selectContactListCommand = selectContactListCommand;
		this.selectContactByNoCommand = selectContactByNoCommand;
		this.insertContactCommand = insertContactCommand;
		this.updateContactCommand = updateContactCommand;
		this.deleteContactCommand = deleteContactCommand;
	}
	
	
	@GetMapping(value="/")
	public String index() {
		logger.info("index() 호출");
		return "index";  // index.jsp로 포워드
	}
	
	@GetMapping(value="selectContactList.do")
	public String selectContactList(Model model) {
		logger.info("selectContactList() 호출");
		selectContactListCommand.execute(model);
		return "list";  // list.jsp로 포워드
	}
	
	@GetMapping(value="selectContactByNo.do")
	public String selectContactByNo(@RequestParam("no") long no,
									Model model) {
		logger.info("selectContactByNo() 호출");
		model.addAttribute("no", no);
		selectContactByNoCommand.execute(model);
		return "view";  // view.jsp로 포워드
	}
	
	@GetMapping(value="insertContactPage.do")
	public String insertContactPage() {
		logger.info("insertContactPage() 호출");
		return "insert";  // insert.jsp로 포워드
	}
	
	@GetMapping(value="insertContact.do")
	public void insertContact(HttpServletRequest request,
							  HttpServletResponse response,
							  Model model) {
		logger.info("insertContact() 호출");
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		insertContactCommand.execute(model);
//		return "redirect:selectContactList.do";
	}
	
	@PostMapping(value="updateContact.do")
	public void updateContact(HttpServletRequest request,
								HttpServletResponse response,
								Model model) {
		logger.info("updateContact() 호출");
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		updateContactCommand.execute(model);
//		return "redirect:selectContactList.do";
	}
	
	@GetMapping(value="deleteContact.do")
	public void deleteContact(@RequestParam("no") long no,
								HttpServletResponse response,
								Model model) {
		logger.info("deleteContact() 호출");
		model.addAttribute("no", no);
		model.addAttribute("response", response);
		deleteContactCommand.execute(model);
//		return "redirect:selectContactList.do";
	}
	
	
	
	
	
	
}