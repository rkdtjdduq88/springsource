package com.spring.domain;

import lombok.ToString;

import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class AddDTO {
	private int num1;
	private int num2;
}
