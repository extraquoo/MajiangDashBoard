package com.majiang.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="WALLET_ID")
	private Wallet wallet;

	
	public Player() {
	}

	public Player(int id, String name, Wallet wallet) {
		this.id=id;
	    this.name=name;
	    this.wallet = wallet;
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

	
	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	}
