package com.spring.memo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.memo.entity.Member;

@SpringBootTest
public class MemberRepositoryTest {
	
	@Autowired
	private MemberRepository repository;
	
	@Test
	public void createMember() {
		Member member = Member.builder()
							  .name("성춘향")
							  .email("tjdcnsgid@gamil.com")
							  .password("tjdcnsgid")
							  .address("강원도 강릉시")
							  .build();
		repository.save(member);
	}
}
