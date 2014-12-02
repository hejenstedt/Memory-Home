package se.lina.players;

import java.util.ArrayList;

import se.lina.model.GameObserver;
import se.lina.model.Tile;

public class Players implements GameObserver, PlayerEventPublisher {

	private Player currentPlayer;
	private ArrayList<Player> players;
	private ArrayList<PlayerEventObserver> observers;
	
	public Players() {
		players = new ArrayList<>();
		observers = new ArrayList<>();
	}

	public void addPlayer(Player player) {
		players.add(player);
		if (currentPlayer == null) {
			currentPlayer = players.get(0);
			players.get(0).setToCurrentPlayer();
		}
		publish(player);
	}

	private void nextPlayer() {

		int position = players.indexOf(currentPlayer);
		if (position < players.size() - 1) {
			currentPlayer.setNotLongerCurrentPlayer();
			currentPlayer = players.get(position + 1);
			currentPlayer.setToCurrentPlayer();
		} else if (position == players.size() - 1) {
			firstPlayerSetToCurrentPlayer();
		}

	}

	private void firstPlayerSetToCurrentPlayer() {
		currentPlayer.setNotLongerCurrentPlayer();
		currentPlayer = players.get(0);
		currentPlayer.setToCurrentPlayer();
	}

	@Override
	public void tileTurned(Tile tile, int row, int column) {
		// TODO: Remove me

	}

	@Override
	public void gameTurnResult(boolean wasMatch, Tile selectedTile,
			Tile lastTile) {

		if (!wasMatch) {
			nextPlayer();
			publish(currentPlayer);
			System.out.println(currentPlayer.getName()+"'s turn");
			//TODO: remove line
		}
		if (wasMatch) {
			currentPlayer.increaseScore();
			publish(currentPlayer);
			System.out.println(currentPlayer.getName()+" "+currentPlayer.getScore());
			//TODO: remove line
		}
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	@Override
	public void publish(Player player) {
		observers.forEach(t->t.playerSettingsChanged(players));
	}

	@Override
	public void register(PlayerEventObserver observer) {
		observers.add(observer);
	}

	@Override
	public void gameOver() {
		//TODO: find player with highest score
		Player winner = null;
		int score = 0;
		for (Player player: players) {
			if (player.getScore()>score) {
				winner = player;
				score = winner.getScore();
			}
		}
		final Player temp = winner;
		observers.forEach(t-> t.winnerFound(temp));
	}

	@Override
	public void reStartBoard() {
		players.forEach(p -> p.score=0);
		
		firstPlayerSetToCurrentPlayer();
			
		observers.forEach(t-> t.playerSettingsChanged(players));
	}

	@Override
	public void reStartBoardWithNewSize(int rows, int columns) {
		players.forEach(p -> p.score=0);
			
		observers.forEach(t-> t.playerSettingsChanged(players));
	}
	

}
