package se.lina.model;

public interface ModelObserver {

	void tileTurned(Tile tile, int row, int column);

	void gameTurnResult(boolean wasMatch);
	
}
