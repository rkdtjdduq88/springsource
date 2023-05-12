package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
	// http://localhost:8080/board/read ==> views/board/read.jsp 보여주기 (경로와 결과 주소가 같다면 void)
	//@RequestMapping(value="/read", method=RequestMethod.GET)
	@GetMapping("/read")
	public void read() {
		log.info("read 요청");		
	}
	// http://localhost:8080/board/read ==> views/board/register.jsp 보여주기 (경로와 결과 주소가 다르다면 void)
//	@RequestMapping(value="/read", method=RequestMethod.GET)
//	public String register() {
//		log.info("register 요청");
//		return "register";
//	}
	//@RequestMapping(value="/register", method=RequestMethod.GET)
	@GetMapping("/register")
	public void register() {
		log.info("register 요청");	
	}
	//@RequestMapping(value="/modify", method=RequestMethod.GET)
	@GetMapping("/modify")
	public void modify() {
		log.info("modify 요청");		
	}
	//@RequestMapping(value="/remove", method=RequestMethod.GET)
	@GetMapping("/remove")
	public void remove() {
		log.info("remove 요청");		
	}
	
}
