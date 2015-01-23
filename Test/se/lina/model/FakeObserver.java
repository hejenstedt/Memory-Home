package se.lina.model;

public 	class FakeObserver implements GameObserver {

	private boolean tileTurned;

	public boolean hasRecievedTileTurnedMessage(){
		return tileTurned;
	}
	
	@Override
	public void tileTurned(Tile tile, int row, int column) {
		this.tileTurned = true;
	}

	@Override
	public void gameOver() {
	}

	@Override
	public void reStartBoard() {
		
	}

	@Override
	public void reStartBoardWithNewSize(int rows, int columns) {
		
	}

	@Override
	public void gameTurnResult(boolean wasMatch, Tile selectedTile,
			Tile lastTile) {
		
	}
	
}