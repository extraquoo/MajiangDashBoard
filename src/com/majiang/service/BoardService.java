package com.majiang.service;

import java.util.List;

import com.majiang.model.Board;

public interface BoardService {

	Board findByBoardId(int id);
	
	List<Board> findAllBoards();

	void saveOrUpdate(Board board);
	
	void saveGameBoard(Board board);
	
	Board CalulateAmount(Board board);
}
