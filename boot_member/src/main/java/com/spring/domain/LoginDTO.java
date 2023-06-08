package com.spring.domain;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString @Getter @Setter
public class LoginDTO {
	private String userid;
	private String password;	
}
