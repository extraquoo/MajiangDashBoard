package com.majiang.dao;

import java.util.List;

import com.majiang.model.Board;

public interface BoardDao {

    List<Board> findAllBoards();
	
	Board findByBoardId(int id);
	
	void save(Board board);

	void update(Board board);
}
