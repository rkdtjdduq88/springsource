package com.spring.mapper;

import com.spring.domain.MemberDTO;

public interface MemberMapper {
	public int register(MemberDTO dto);
	public MemberDTO read(String userid);
}
