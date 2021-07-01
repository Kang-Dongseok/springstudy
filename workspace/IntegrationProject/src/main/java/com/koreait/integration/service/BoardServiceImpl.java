package com.koreait.integration.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.koreait.integration.domain.Board;
import com.koreait.integration.repository.BoardRepository;

public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository repository;
	
	@Override
	public List<Board> totalList() {
		return repository.selectAll();
	}

	@Override
	public List<Board> searchList(Map<String, String> map) {
		return repository.selectQuery(map);
	}

}
