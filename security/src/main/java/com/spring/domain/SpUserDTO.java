package com.spring.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class SpUserDTO {
	private String userid;
	private String email;
	private String password;
	private boolean enabled;
	
	private List<SpUserAuthorityDTO> authorites;
}
