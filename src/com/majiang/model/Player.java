package com.majiang.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "PLAYER")
public class Player implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int id;
	
	@Column(name = "PLAYER_NAME", unique = true, nullable = false, length = 20)
	private String name;
	
	@Column(name = "PLAYER_WALLET_AMOUNT", length= 11)
	private BigDecimal walletAmount;
	
	public Player() {
	}

	public Player(int id, String name, BigDecimal walletAmount) {
		this.id=id;
	    this.name=name;
	    this.walletAmount = walletAmount;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getWalletAmount() {
		return walletAmount;
	}

	public void setWalletAmount(BigDecimal walletAmount) {
		this.walletAmount = walletAmount;
	}

	
	
	}
