package se.lina.model;

public interface GameBoardEventPublisher {

	public void publish(int row, int column, Tile tile);

	public void register(GameObserver modelObserver);

}
