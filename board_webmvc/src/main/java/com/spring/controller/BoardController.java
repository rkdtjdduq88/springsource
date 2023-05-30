package com.spring.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.BoardDTO;
import com.spring.domain.Criteria;
import com.spring.domain.PageDTO;
import com.spring.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService service;
	
	@GetMapping("/list")
	public void listGet(@ModelAttribute("cri") Criteria cri, Model model) {
		log.info("전체 리스트 요청");
		log.info("type "+Arrays.toString(cri.getTypeArr()));
		// 사용자가 요청한 번호에 맞는 게시물 목록 요청
		List<BoardDTO> list = service.getList(cri);
		// 전체 게시물 숫자
		int total = service.getTotalCnt(cri);
		
		model.addAttribute("list", list);
		model.addAttribute("pageDTO", new PageDTO(cri, total));
	}
	@GetMapping("/register")
	public void registerGet() {
		log.info("글쓰기 폼 요청");
	}
	@PostMapping("/register")
	public String registerPost(BoardDTO dto,RedirectAttributes rttr, Criteria cri) {
		log.info("글쓰기 등록 요청");
		
		// 첨부파일 확인
//		if(dto.getAttachList() != null) {
//			dto.getAttachList().forEach(attach -> log.info(attach.toString()));
//		}
		
		if(service.insert(dto)) {
			
			log.info("글 번호 : "+dto.getBno());
			
			rttr.addFlashAttribute("result", dto.getBno());
			// 페이지 나누기 정보 주소줄에 같이 보내기
			rttr.addAttribute("page", cri.getPage());
			rttr.addAttribute("amount", cri.getAmount());
			return "redirect:/board/list";			
		}
		return "/board/register";		
	}
	// http://localhost:8080/board/read?page5&amount=10&bno=22
	// http://localhost:8080/board/modify?bno=22
	@GetMapping({"/read","/modify"})
	public void readGet(int bno, Model model,@ModelAttribute("cri") Criteria cri) {
		log.info("글 내용 보기");		
		// bno에 맞는 내용 가져오기
		BoardDTO dto = service.getRow(bno);
		model.addAttribute("dto", dto);		
	}
	@PostMapping("/modify")
	public String modifyPost(BoardDTO dto,RedirectAttributes rttr, Criteria cri) {		
		// 성공 시 리스트
		log.info("내용 수정 "+cri);		
		service.update(dto); 
		rttr.addFlashAttribute("result", "수정이 완료되었습니다.");
		
		// 페이지 나누기 정보 주소줄에 같이 보내기
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("amount", cri.getAmount());
		// 검색 정보 주소줄에 보내기
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";			
	}
	@GetMapping("/remove")
	public String removeGet(int bno,RedirectAttributes rttr, Criteria cri) {
		log.info("게시글 삭제");
		service.delete(bno);
		rttr.addFlashAttribute("result", "삭제가 완료되었습니다.");
		
		// 페이지 나누기 정보 주소줄에 같이 보내기
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("amount", cri.getAmount());
		// 검색 정보 주소줄에 보내기
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";
	}
}
