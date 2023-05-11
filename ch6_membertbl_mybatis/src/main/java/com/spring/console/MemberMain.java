package com.spring.console;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.domain.MemberDTO;
import com.spring.service.MemberService;

public class MemberMain {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		MemberService service = (MemberService) ctx.getBean("member");
		
		// 전체 조회
//		List<MemberDTO> list = service.getRows();
//		for (MemberDTO memberDTO : list) {
//			System.out.println(memberDTO);
//		}		
		
		// 특정 조회
//		System.out.println(service.getRow("kang123@", "kang123@"));
		
		// 회원가입
//		MemberDTO dto = new MemberDTO("java", "java", "강찍", "남", "java@java.com");
//		boolean result = service.insertMember(dto);
//		System.out.println(result?"회원가입성공":"회원가입실패");
		
		// 회원정보수정
		MemberDTO dto = new MemberDTO();
		dto.setUserid("java");
		dto.setPassword("java");
		dto.setEmail("rkdtjdduq88@naver.com");
		boolean result = service.updateMember(dto);
		System.out.println(result?"회원정보수정성공":"회원정보수정실패");
		
		// 회원탈퇴
//		System.out.println(service.deleteMember("kang123@", "kang123@"));
		
	}
}
