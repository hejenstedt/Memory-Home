package se.lina.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MemoryBoard implements GameBoardEventPublisher {

	private final ArrayList<ModelObserver> observers;
	private Tile[][] board;

	private Tile lastTile;
	private int noOfTilesTurned;
	
	
	public MemoryBoard(int rows, int columns) {
		observers = new ArrayList<>();
		board = new Tile[rows][columns];
		fillBoard();
		noOfTilesTurned=0;
	}

	private void fillBoard() {
		List<Tile> tileList = new LinkedList<>();

		for (int i = 0; i < noOfPairs(); i++) {
			tileList.add(new Tile(i + ""));
			tileList.add(new Tile(i + ""));
		}

		Collections.shuffle(tileList);

		Iterator<Tile> iterator = tileList.iterator();
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				board[row][col] = iterator.next();
			}
		}

	}

	private int noOfPairs() {
		return board.length * board[0].length / 2;
	}

	public void turnTileUp(int row, int column) {
		Tile selectedTile = board[row][column];
		if (selectedTile.isFaceUp()) {
			return;
		}
		selectedTile.turnFaceUp();
		publish(row, column, selectedTile);
		
		if (noOfTilesTurned==0) {
			noOfTilesTurned=1;
			this.lastTile = selectedTile;
		} else if (noOfTilesTurned==1) {
			noOfTilesTurned=0;
			boolean matches = selectedTile.isMatch(lastTile);
			if(!matches){
				turnTilesBack(row, column, selectedTile);
			}
			observers.forEach(t -> t.gameTurnResult(matches));
		}
		
	}

	private void turnTilesBack(int row, int column, Tile selectedTile) {
		lastTile.turnFaceDown();
		selectedTile.turnFaceDown();
		publish(getRowIndexOf(lastTile), getColumnIndexOf(lastTile), lastTile);
		publish(row, column, selectedTile);
	}

	int getRowIndexOf(Tile tile){
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col] == tile){
					return row;
				}
			}
		}
		
		throw new IllegalStateException("No Tile found");
	}
	
	int getColumnIndexOf(Tile tile){
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col] == tile){
					return col;
				}
			}
		}
		
		throw new IllegalStateException("No Tile found");	
	}
	
	@Override
	public void publish(int row, int column, Tile tile) {
		observers.forEach(t -> t.tileTurned(tile, row, column));
	}

	@Override
	public void register(ModelObserver modelObserver) {
		observers.add(modelObserver);
	}

}
