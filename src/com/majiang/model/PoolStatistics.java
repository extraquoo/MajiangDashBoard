package com.majiang.model;

import java.math.BigDecimal;

import com.google.gson.annotations.SerializedName;

public class PoolStatistics {

	@SerializedName("id")
	private int board_sequence;
	
	@SerializedName("one")
	private BigDecimal playerOnePool;
	
	@SerializedName("two")
	private BigDecimal playerTwoPool;

	@SerializedName("three")
	private BigDecimal playerThreePool;
	
	@SerializedName("four")
	private BigDecimal playerFourPool;

	public BigDecimal getPlayerOnePool() {
		return playerOnePool;
	}

	public void setPlayerOnePool(BigDecimal playerOnePool) {
		this.playerOnePool = playerOnePool;
	}

	public BigDecimal getPlayerTwoPool() {
		return playerTwoPool;
	}

	public void setPlayerTwoPool(BigDecimal playerTwoPool) {
		this.playerTwoPool = playerTwoPool;
	}

	public BigDecimal getPlayerThreePool() {
		return playerThreePool;
	}

	public void setPlayerThreePool(BigDecimal playerThreePool) {
		this.playerThreePool = playerThreePool;
	}

	public BigDecimal getPlayerFourPool() {
		return playerFourPool;
	}

	public void setPlayerFourPool(BigDecimal playerFourPool) {
		this.playerFourPool = playerFourPool;
	}

	public int getBoard_sequence() {
		return board_sequence;
	}

	public void setBoard_sequence(int board_sequence) {
		this.board_sequence = board_sequence;
	}
	
	
	
}
