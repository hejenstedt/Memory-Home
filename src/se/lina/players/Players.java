package se.lina.players;

import java.util.ArrayList;

import se.lina.model.GameObserver;
import se.lina.model.Tile;

public class Players implements GameObserver,PlayerEventPublisher {

	Player currentPlayer;
	ArrayList<Player> players;
	ArrayList<PlayerEventObserver> observers;
	
	public Players() {
		players = new ArrayList<>();
		observers = new ArrayList<>();
	}

	public void addPlayer(Player player) {
		players.add(player);
		publish(player);
		if (currentPlayer == null) {
			currentPlayer = players.get(0);	
		}
	}

	private void nextPlayer() {

		int position = players.indexOf(currentPlayer);
		if (position < players.size() - 1) {
			currentPlayer = players.get(position + 1);
		} else if (position == players.size() - 1) {
			currentPlayer = players.get(0);
		}

	}

	@Override
	public void tileTurned(Tile tile, int row, int column) {
		// TODO: Remove me

	}

	@Override
	public void gameTurnResult(boolean wasMatch) {

		if (!wasMatch) {
			nextPlayer();
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

}
