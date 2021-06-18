package com.koreait.contact02.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.contact02.command.DeleteContactCommand;
import com.koreait.contact02.command.InsertContactCommand;
import com.koreait.contact02.command.SelectContactByNoCommand;
import com.koreait.contact02.command.SelectContactListCommand;
import com.koreait.contact02.command.UpdateContactCommand;
import com.koreait.contact02.config.BeanConfiguration;

@Controller
public class ContactController {

	// field
	private AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
	
	@GetMapping(value="/")
	public String index() {
		return "index";  // index.jsp로 포워드
	}
	
	@GetMapping(value="selectContactList.do")
	public String selectContactList(Model model) {
		SelectContactListCommand selectContactListCommand = ctx.getBean("listCommand", SelectContactListCommand.class);
		selectContactListCommand.execute(model);
		return "list";  // list.jsp로 포워드
	}
	
	@GetMapping(value="selectContactByNo.do")
	public String selectContactByNo(@RequestParam("no") long no,
									Model model) {
		model.addAttribute("no", no);
		SelectContactByNoCommand selectContactByNoCommand = ctx.getBean("viewCommand", SelectContactByNoCommand.class);
		selectContactByNoCommand.execute(model);
		return "view";  // view.jsp로 포워드
	}
	
	@GetMapping(value="insertContactPage.do")
	public String insertContactPage() {
		return "insert";  // insert.jsp로 포워드
	}
	
	@GetMapping(value="insertContact.do")
	public void insertContact(HttpServletRequest request,
							  HttpServletResponse response,
							  Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		InsertContactCommand insertContactCommand = ctx.getBean("insertCommand", InsertContactCommand.class);
		insertContactCommand.execute(model);
//		return "redirect:selectContactList.do";
	}
	
	@PostMapping(value="updateContact.do")
	public void updateContact(HttpServletRequest request,
								HttpServletResponse response,
								Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		UpdateContactCommand updateContactCommand = ctx.getBean("updateCommand", UpdateContactCommand.class);
		updateContactCommand.execute(model);
//		return "redirect:selectContactList.do";
	}
	
	@GetMapping(value="deleteContact.do")
	public void deleteContact(@RequestParam("no") long no,
								HttpServletResponse response,
								Model model) {
		model.addAttribute("no", no);
		model.addAttribute("response", response);
		DeleteContactCommand deleteContactCommand = ctx.getBean("deleteCommand", DeleteContactCommand.class);
		deleteContactCommand.execute(model);
//		return "redirect:selectContactList.do";
	}
	
	
}