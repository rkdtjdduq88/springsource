package com.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.AuthDTO;
import com.spring.domain.ChangeDTO;
import com.spring.domain.LoginDTO;
import com.spring.domain.MemberDTO;
import com.spring.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	// login.jsp 보여주는 컨트롤러 작성
	@GetMapping("/login")
	public void loginGet() {
		log.info("로그인 폼 요청");
	}
	@PostMapping("/login")
	public String loginPost(LoginDTO loginDTO,HttpSession session) {
		log.info("로그인 요청"+loginDTO);
		AuthDTO authDTO = service.login(loginDTO);
		if(authDTO !=null) {
			session.setAttribute("authDTO", authDTO);					
			return "redirect:/";
		}else {
			return "redirect:/member/login";
		}
	}
	@GetMapping("/logout")
	public String logoutGet(HttpSession session) {
		log.info("로그아웃 요청");		
		session.removeAttribute("authDTO");
		return "redirect:/";
	}
	@GetMapping("/step1")
	public void stepGet() {
		log.info("약관 페이지 보여주기");
		
	}
	@PostMapping("/step1")
	public String stepPost(boolean agree,RedirectAttributes rttr){
		log.info("약관동의"+agree);
		// 약관동의 여부		
		if(agree) 
			// true 인경우 register.jsp 보여주기
			return "/member/register";			
		else {
			// false 인 경우 step1.jsp 보여주기
			rttr.addFlashAttribute("check", false);
			return "redirect:/member/step1";
			//return "/member/step1"; // ==> View Resolver
		}
		
	}
	@PostMapping("/register")
	public String registerPost(MemberDTO dto,Model model) {
		log.info("회원가입"+dto);
		boolean memberDTO = service.register(dto);
		model.addAttribute("memberDTO", memberDTO);
		return "redirect:/member/login";		
	}	
	
	// 중복 아이디
	@PostMapping("/dupId")
	@ResponseBody // 컨트롤러 작업이 완료될 때 결과값으로 리턴시킴(ViewResolver 동작시키지 않음)
	public String dupIdCheck(String userid) {
		log.info("중복 아이디 체크 "+userid);
		boolean idCheck = service.dupId(userid);
		if(idCheck) {
			return "true"; // /WEB-INF/views/true.jsp 찾으려고 함
		}else {
			return "false"; // /WEB-INF/views/false.jsp
		}
		
	}
	@GetMapping("/leave")
	public void leaveGet() {
		log.info("회원 탈퇴 요청");
	}
	@PostMapping("/leave")
	public String leavePost(LoginDTO loginDTO,HttpSession session) {
		log.info("회원 탈퇴 "+loginDTO);		 
		if(service.remove(loginDTO)) {
			session.invalidate();
			return "redirect:/";			
		}else {
			return "redirect:/member/leave";
		}
	}
	@GetMapping("/changePwd")
	public void changePwdGet() {
		log.info("비밀번호 변경 페이지 요청");
	}
	@PostMapping("/changePwd")
	public String changePwdPost(ChangeDTO changeDTO,HttpSession session) {
		log.info("비밀번호 변경 요청 "+changeDTO);	
		
		if(changeDTO.passwordEquals()) {
			// 현재 비밀번호 일치 확인		
			// true : 비밀번호 변경 ==> session 제거 ==> 로그인 페이지 보여주기
			// false : 회원탈퇴 페이지 보여주기
			if(service.update(changeDTO)) {			
				session.invalidate();
				return "redirect:/member/login";			
			}			
		}
		return "redirect:/member/leave";		
	}
}






















