package com.spring.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter @ToString
public class BoardDTO {
	private int bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updatedate;
}
