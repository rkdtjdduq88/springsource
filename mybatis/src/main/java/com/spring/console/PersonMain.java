package com.spring.console;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.domain.PersonDTO;
import com.spring.service.PersonService;

public class PersonMain {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		PersonService service = (PersonService) ctx.getBean("person");
		
		// 삽입
		PersonDTO dto = new PersonDTO("hong123", "홍길동");
//		System.out.println(service.insertPerson(dto)?"삽입성공":"삽입실패");
		
		// 업데이트(수정)
//		System.out.println(service.updatePerson(dto)?"수정성공":"수정실패");
		
		// 삭제
//		System.out.println(service.deletePerson("hong123")?"삭제성공":"삭제실패");
//		
		// 전체 조회
//		List<PersonDTO> list = service.getRows();
//		for (PersonDTO personDTO : list) {
//			System.out.println(personDTO);
//		}
		
		// 특정 조회
		System.out.println(service.getRow("kang123"));
	}
}
