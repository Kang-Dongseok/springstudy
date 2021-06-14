package com.koreait.mvc03.controller;

import org.springframework.beans.factory.wiring.BeanConfigurerSupport;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.mvc03.dto.Person;

@Controller
public class MyController5 {

	//
	//
	//
	
	// field
	private AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfigurerSupport.class);
	
	@RequestMapping("f4/v01")
	public String a(Model model) {
		
		Person m = ctx.getBean("man", Person.class);
		model.addAttribute("m", m);
		
		return "folder04/view01";
		
	}
	
}
 