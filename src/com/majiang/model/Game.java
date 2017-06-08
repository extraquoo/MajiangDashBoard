package com.majiang.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "GAME")
@NamedQueries(
		{
			@NamedQuery(name = "findPlayerOne", query = "select g from Game g where g.id =:id" ),
			@NamedQuery(name = "findPlayerTwo", query = "select g from Game g where g.id =:id" ),
			@NamedQuery(name = "findPlayerThree", query = "select g from Game g where g.id =:id" ),
			@NamedQuery(name = "findPlayerFour", query = "select g from Game g where g.id =:id" )
   		}
	)
public class Game  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int id;
		
	@Column(name = "PLAYER_ONE",  length = 20)
	private String playerOne;
	@Column(name = "PLAYER_TWO",  length = 20)
	private String playerTwo;
	@Column(name = "PLAYER_THREE", length = 20)
	private String playerThree;
	@Column(name = "PLAYER_FOUR",  length = 20)
	private String playerFour;
	
	@Column(name = "DRAW_COUNT")
	private int drawCount;
	
	@Column(name = "BASE_AMOUNT")
	private BigDecimal baseAmount;
	
	@Column(name = "FLOWER_AMOUNT")
	private BigDecimal flowerAmount;
	
	@Column(name = "MAX_FLOWERS")
	private int maxFlowers;
	
	@Column(name = "START_DATE")
	private Date startDate;
	
	@Column(name = "END_DATE")
	private Date endDate;
	
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY , mappedBy="game")
	private List<Board> boards;
	
	public Game(){
		
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlayerOne() {
		return playerOne;
	}
	public void setPlayerOne(String playerOne) {
		this.playerOne = playerOne;
	}
	public String getPlayerTwo() {
		return playerTwo;
	}
	public void setPlayerTwo(String playerTwo) {
		this.playerTwo = playerTwo;
	}
	public String getPlayerThree() {
		return playerThree;
	}
	public void setPlayerThree(String playerThree) {
		this.playerThree = playerThree;
	}
	public String getPlayerFour() {
		return playerFour;
	}
	public void setPlayerFour(String playerFour) {
		this.playerFour = playerFour;
	}
	public int getDrawCount() {
		return drawCount;
	}
	public void setDrawCount(int drawCount) {
		this.drawCount = drawCount;
	}
	public BigDecimal getBaseAmount() {
		return baseAmount;
	}
	public void setBaseAmount(BigDecimal baseAmount) {
		this.baseAmount = baseAmount;
	}
	public BigDecimal getFlowerAmount() {
		return flowerAmount;
	}
	public void setFlowerAmount(BigDecimal flowerAmount) {
		this.flowerAmount = flowerAmount;
	}

	public List<Board> getBoards() {
		return boards;
	}

	public void setBoards(List<Board> boards) {
		this.boards = boards;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public int getMaxFlowers() {
		return maxFlowers;
	}


	public void setMaxFlowers(int maxFlowers) {
		this.maxFlowers = maxFlowers;
	}
	
	
}
