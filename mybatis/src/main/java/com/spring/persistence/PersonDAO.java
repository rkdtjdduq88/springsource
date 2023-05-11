package com.spring.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.domain.PersonDTO;

@Repository
public class PersonDAO {
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace="com.spring.mappers.PersonMapper";
	
	// 삽입
	public int insert(PersonDTO insert) {
		return sqlSession.insert(namespace+".insertPerson", insert);
	}
	
	// 업데이트(수정)
	public int update(PersonDTO update) {
		return sqlSession.update(namespace+".updatePerson", update);
	}
	
	// 삭제
	public int delete(String id) {
		return sqlSession.delete(namespace+".deletePerson", id);
	}	
	
	// 특정 조회
	public PersonDTO getRow(String id) {
		return sqlSession.selectOne(namespace+".selectOne", id);
	}
	
	// 전체 조회
	public List<PersonDTO> getRows(){
		return sqlSession.selectList(namespace+".selectAll");
	}
}
