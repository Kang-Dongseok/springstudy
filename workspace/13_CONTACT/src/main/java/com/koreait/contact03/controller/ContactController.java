package com.koreait.contact03.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.contact03.command.DeleteContactCommand;
import com.koreait.contact03.command.InsertContactCommand;
import com.koreait.contact03.command.SelectContactByNoCommand;
import com.koreait.contact03.command.SelectContactListCommand;
import com.koreait.contact03.command.UpdateContactCommand;

@Controller
public class ContactController {

	// field
	@Autowired
	private SqlSession sqlSession;
	private SelectContactListCommand selectContactListCommand;
	private SelectContactByNoCommand selectContactByNoCommand;
	private InsertContactCommand insertContactCommand;
	private UpdateContactCommand updateContactCommand;
	private DeleteContactCommand deleteContactCommand;

	
	// constructor
	@Autowired
	public ContactController(SqlSession sqlSession, SelectContactListCommand selectContactListCommand,
			SelectContactByNoCommand selectContactByNoCommand, InsertContactCommand insertContactCommand,
			UpdateContactCommand updateContactCommand, DeleteContactCommand deleteContactCommand) {
		super();
		this.sqlSession = sqlSession;
		this.selectContactListCommand = selectContactListCommand;
		this.selectContactByNoCommand = selectContactByNoCommand;
		this.insertContactCommand = insertContactCommand;
		this.updateContactCommand = updateContactCommand;
		this.deleteContactCommand = deleteContactCommand;
	}
	
	
	@GetMapping(value="/")
	public String index() {
		return "contact/index";  // index.jsp로 포워드
	}
	
	@GetMapping(value="selectContactList.do")
	public String selectContactList(Model model) {
		selectContactListCommand.execute(sqlSession, model);
		return "contact/list";  // contact/list.jsp로 포워드
	}
	
	@GetMapping(value="selectContactByNo.do")
	public String selectContactByNo(@RequestParam("no") long no,
									Model model) {
		model.addAttribute("no", no);
		selectContactByNoCommand.execute(sqlSession, model);
		return "contact/view";  // view.jsp로 포워드
	}
	
	@GetMapping(value="insertContactPage.do")
	public String insertContactPage() {
		return "contact/insert";  // insert.jsp로 포워드
	}
	
	@GetMapping(value="insertContact.do")
	public void insertContact(HttpServletRequest request,
							  HttpServletResponse response,
							  Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		insertContactCommand.execute(sqlSession, model);
//		return "redirect:selectContactList.do";
	}
	
	@PostMapping(value="updateContact.do")
	public void updateContact(HttpServletRequest request,
								HttpServletResponse response,
								Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		updateContactCommand.execute(sqlSession, model);
//		return "redirect:selectContactList.do";
	}
	
	@GetMapping(value="deleteContact.do")
	public void deleteContact(@RequestParam("no") long no,
								HttpServletResponse response,
								Model model) {
		model.addAttribute("no", no);
		model.addAttribute("response", response);
		deleteContactCommand.execute(sqlSession, model);
//		return "redirect:selectContactList.do";
	}
	
}