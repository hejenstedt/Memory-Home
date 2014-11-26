package se.lina.model;

public interface GameObserver {

	void tileTurned(Tile tile, int row, int column);

	void gameTurnResult(boolean wasMatch);

}
