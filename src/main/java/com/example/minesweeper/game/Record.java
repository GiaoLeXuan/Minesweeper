package com.example.minesweeper.game;

public class Record {
	private int rank;
	private String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Record(String time) {
		super();
		this.time = time;
	}
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Record(int rank, String time) {
		super();
		this.rank = rank;
		this.time = time;
	}
	

	
}
