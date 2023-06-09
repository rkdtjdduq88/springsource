package com.spring.mapper;

import java.util.List;

import com.spring.domain.BoardDTO;

public interface BoardMapper {
	public int insert(BoardDTO dto);
	public int update(BoardDTO dto);
	public int delete(int bno);
	public BoardDTO getRow(int bno);
	public List<BoardDTO> getRows();
}
