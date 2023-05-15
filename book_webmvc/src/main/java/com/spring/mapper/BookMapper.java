package com.spring.mapper;

import java.util.List;

import com.spring.domain.BookDTO;

public interface BookMapper {
	public List<BookDTO> getRows();
	public BookDTO read(int code);
	public int insert(BookDTO dto);
	public int delete(int code);
	
}
