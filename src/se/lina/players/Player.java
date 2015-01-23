package se.lina.players;

public class Player {

	String name;
	int score;
	int noOfMoves;
	private boolean isCurrentPlayer;
	
	public Player(String name) {
		this.name = name;
		this.score = 0;
		isCurrentPlayer= false;
		noOfMoves=0;
	}
	
	public String getName() {
		return name;
	}
	
	public void increaseNoOfMoves(){
		noOfMoves++;
	}
	
	public int getNoOfMoves() {
		return noOfMoves;
	}
	
	public void resetNoOfMoves(){
		noOfMoves=0;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void increaseScore() {
		score++;
	}

	public boolean isCurrentPlayer() {
		return isCurrentPlayer;
	}

	public void setToCurrentPlayer() {
		this.isCurrentPlayer =true;
	}
	
	public void setNotLongerCurrentPlayer(){
		this.isCurrentPlayer =false;
	}
	
}
