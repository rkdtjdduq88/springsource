package com.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class ChangeDTO {
	// changePwd.jsp 와 일치시키기
	private String userid;
	private String currentPassword;
	private String newPassword;
	private String confirmPassword;
	
	public boolean passwordEquals() {
		return newPassword.equals(confirmPassword);
	}
}
