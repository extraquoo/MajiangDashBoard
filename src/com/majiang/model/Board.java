package com.majiang.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BOARD")
public class Board implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int id;
	
	@Column(name = "GAME_ID", unique = true, nullable = false)
	private int game_id;
	
	@Column(name = "WINNER", length =20 )
	private String winner;
	
	@Column(name = "WIN_TYPE", length =20 )
	private String winType;
	
	@Column(name = "HAND_TYPE", length =20 )
	private String handType;
	
	@Column(name = "FLOWERS", length =2 )
	private int flowers;
	
	@Column(name = "LOSER", length =20 )
	private String loser;
	
	@Column(name = "WIN_AMOUNT", length =11 )
	private BigDecimal winAmount;
	
	@Column(name = "CONTRACT_ONE", length =20 )
	private String contractOne;
	
	@Column(name = "CONTRACT_TWO", length =20 )
	private String contractTwo;
	
	@Column(name = "CONTRACT_THREE", length =20 )
	private String contractThree;
		
	@Column(name = "COMMENT", length =200 )
	private String comment;
	
	@Column(name = "BOARD_RECORD_DATE")
	private Date boardDate;
	
	@Column(name = "PLAYER_ONE_STAKE", length =11 )
	private BigDecimal playerOneStake;
	
	@Column(name = "PLAYER_TWO_STAKE", length =11 )
	private BigDecimal playerTwoStake;
	
	@Column(name = "PLAYER_THREE_STAKE", length =11 )
	private BigDecimal playerThreeStake;
	
	@Column(name = "PLAYER_FOUR_STAKE", length =11 )
	private BigDecimal playerFourStake;
	
	@Column(name = "CLEAR_DOOR" , length =3 )
	private Boolean clearDoor;
	
	@ManyToOne(fetch=FetchType.LAZY )
	@JoinColumn(name="GAME_ID" ,insertable = false, updatable = false)
	private Game game;
	
	public Board(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGame_id() {
		return game_id;
	}

	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getWinType() {
		return winType;
	}

	public void setWinType(String winType) {
		this.winType = winType;
	}

	public String getHandType() {
		return handType;
	}

	public void setHandType(String handType) {
		this.handType = handType;
	}

	public String getLoser() {
		return loser;
	}

	public void setLoser(String loser) {
		this.loser = loser;
	}

	public BigDecimal getWinAmount() {
		return winAmount;
	}

	public void setWinAmount(BigDecimal winAmount) {
		this.winAmount = winAmount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	public int getFlowers() {
		return flowers;
	}
	public void setFlowers(int flowers) {
		this.flowers = flowers;
	}

	public String getContractOne() {
		return contractOne;
	}

	public void setContractOne(String contractOne) {
		this.contractOne = contractOne;
	}

	public String getContractTwo() {
		return contractTwo;
	}

	public void setContractTwo(String contractTwo) {
		this.contractTwo = contractTwo;
	}

	public String getContractThree() {
		return contractThree;
	}

	public void setContractThree(String contractThree) {
		this.contractThree = contractThree;
	}

	public BigDecimal getPlayerOneStake() {
		return playerOneStake;
	}

	public void setPlayerOneStake(BigDecimal playerOneStake) {
		this.playerOneStake = playerOneStake;
	}

	public BigDecimal getPlayerTwoStake() {
		return playerTwoStake;
	}

	public void setPlayerTwoStake(BigDecimal playerTwoStake) {
		this.playerTwoStake = playerTwoStake;
	}

	public BigDecimal getPlayerThreeStake() {
		return playerThreeStake;
	}

	public void setPlayerThreeStake(BigDecimal playerThreeStake) {
		this.playerThreeStake = playerThreeStake;
	}

	public BigDecimal getPlayerFourStake() {
		return playerFourStake;
	}

	public void setPlayerFourStake(BigDecimal playerFourStake) {
		this.playerFourStake = playerFourStake;
	}

	public Boolean getClearDoor() {
		return clearDoor;
	}

	public void setClearDoor(Boolean clearDoor) {
		this.clearDoor = clearDoor;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	
}
