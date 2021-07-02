package com.koreait.mypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.mypage.command.member.JoinCommand;
import com.koreait.mypage.command.member.LeaveCommand;
import com.koreait.mypage.command.member.LoginCommand;
import com.koreait.mypage.command.member.LogoutCommand;

@Controller
public class MemberController {

	@Autowired
	private SqlSession sqlSession;
	private LoginCommand loginCommand;
	private JoinCommand joinCommand;
	private LogoutCommand logoutCommand;	
	private LeaveCommand leaveCommand;
	
	public MemberController(SqlSession sqlSession,
							LoginCommand loginCommand,
							JoinCommand joinCommand,
							LogoutCommand logoutCommand,
							LeaveCommand leaveCommand) {
		super();
		this.sqlSession = sqlSession;
		this.loginCommand = loginCommand;
		this.joinCommand = joinCommand;
		this.logoutCommand = logoutCommand;
		this.leaveCommand = leaveCommand;
	}

	@GetMapping(value = {"/", "index.do"})
	public String index() {
		return "index";
	}
	
	@GetMapping(value="joinPage.do")
	public String joinPage() {
		return "member/join";
	}
	
	@PostMapping(value="join.do")
	public String join(HttpServletRequest request,
					   Model model) {
		model.addAttribute("request", request);
		joinCommand.execute(sqlSession, model);
		return "redirect:/";
	}
	
	@PostMapping(value="login.do")
	public String login(HttpServletRequest request,
						Model model) {
		model.addAttribute("request", request);
		loginCommand.execute(sqlSession, model);
		return "redirect:index.do";
	}
	
	@GetMapping(value="logout.do")
	public String logout(HttpSession session,
						 Model model) {
		model.addAttribute("session", session);
		logoutCommand.execute(sqlSession, model);
		return "redirect:/";
	}
	
	@GetMapping(value="leave.do")
	public String leave(HttpSession session,
						Model model) {
		model.addAttribute("session", session);
		leaveCommand.execute(sqlSession, model);
		return "redirect:/";
	}
	
	@GetMapping(value="galleryPage.do")
	public String galleryPage() {
		return "gallery/gallery";
	}
	
}
