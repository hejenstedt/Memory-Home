package se.lina.players;

import java.util.ArrayList;

import se.lina.model.ModelObserver;
import se.lina.model.Tile;

public class PlayerController implements ModelObserver{

	Player currentPlayer;
	ArrayList<Player> players;

	public PlayerController() {
		players = new ArrayList<>();
		currentPlayer= players.get(0);
	}

	void addPlayer(Player player) {
		players.add(player);
	}

	public void nextPlayer() {
		int position = players.indexOf(currentPlayer);
		if (position < players.size() - 1) {
			currentPlayer = players.get(position + 1);
		} else if (position == players.size()-1) {
			currentPlayer=players.get(0);
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
		}
		if (wasMatch) {
			currentPlayer.increaseScore();
		}
	}

}
