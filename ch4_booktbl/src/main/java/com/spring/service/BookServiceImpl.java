package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.BookDTO;
import com.spring.persistence.BookDAO;

@Service("bookService") // bookServiceImpl
public class BookServiceImpl implements BookService {
	
	@Autowired  // DAO에 만든 @Repository 를 주입시키기(하단에 생성자를 막았기 때문에 nullpointerException이 남)
	private BookDAO bookDAO;
	
//	public BookServiceImpl(BookDAO bookDAO) {
//		this.bookDAO = bookDAO;
//	}
	
	@Override
	public boolean insertBook(BookDTO insertDto) {		
		return bookDAO.insert(insertDto);
	}

	@Override
	public boolean updateBook(BookDTO updateDto) {		
		return bookDAO.update(updateDto);
	}

	@Override
	public boolean deleteBook(int code) {
		return bookDAO.delete(code);
	}

	@Override
	public BookDTO getBook(int code) {
		return bookDAO.getrow(code);
	}

	@Override
	public List<BookDTO> getBookList() {		
		return bookDAO.getRows();
	}

}
