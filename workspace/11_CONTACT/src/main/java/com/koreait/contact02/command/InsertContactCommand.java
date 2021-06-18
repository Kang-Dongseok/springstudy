package com.koreait.contact02.command;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.contact02.dao.ContactDAO;
import com.koreait.contact02.dto.Contact;

public class InsertContactCommand implements ContactCommand {

	@Autowired
	private ContactDAO contactDAO;
	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		Contact contact = new Contact();
		contact.setName(request.getParameter("name"));
		contact.setTel(request.getParameter("tel"));
		contact.setAddr(request.getParameter("addr"));
		contact.setEmail(request.getParameter("email"));
		contact.setNote(request.getParameter("note"));
		
		int result = contactDAO.insertContact(contact);
		
		response.setContentType("text/html; charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			if (result == 0) {
				out.println("<script>");
				out.println("alert('등록 실패')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('등록 성공')");
				out.println("location.href='selectContactList.do'");
				out.println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
