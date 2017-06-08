package com.majiang.model;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class DashBoard {

	@SerializedName("id")
	private int id;
	@SerializedName("winner")
	private String winner;
	@SerializedName("comment")
	private String comments;
	@SerializedName("enddate")
	private Date endDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
