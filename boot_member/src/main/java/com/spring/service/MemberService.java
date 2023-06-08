package com.spring.service;

import java.util.List;

import com.spring.domain.AuthDTO;
import com.spring.domain.ChangeDTO;
import com.spring.domain.LoginDTO;
import com.spring.domain.MemberDTO;

public interface MemberService {
	public AuthDTO login(LoginDTO loginDTO);
	public boolean register(MemberDTO memberDTO);
	public boolean dupId(String userid);
	public boolean remove(LoginDTO loginDTO);
	public boolean update(ChangeDTO changeDTO);
}
