package com.spring.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter @Setter @AllArgsConstructor
public class LoginDTO {
	private String id;
	private String password;	
}
