package se.lina.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MemoryBoard implements GameBoardEventPublisher {

	private final ArrayList<GameObserver> observers;
	private Tile[][] board;
	private Tile lastTile;
	private int noOfTilesTurned;

	public MemoryBoard(int rows, int columns) {
		observers = new ArrayList<>();
		board = new Tile[rows][columns];
		fillBoard();
		noOfTilesTurned = 0;
	}

	public void reStartBoard(){
		noOfTilesTurned = 0;
		fillBoard();
		observers.forEach(t-> t.reStartBoard());
	}
	
	public void reStartBoardWithNewSize(int rows, int columns){
		noOfTilesTurned=0;
		board = new Tile[rows][columns];
		fillBoard();
		//TODO: trigger change in MainWindow
		observers.forEach(t-> t.reStartBoardWithNewSize(rows, columns));
	}
	
	private void fillBoard() {
		List<Tile> tileList = new LinkedList<>();

		for (int i = 0; i < noOfPairs(); i++) {
			//TODO: add different themes 
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

	private boolean isGameOver() {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (!board[i][j].isFaceUp) {
					return false;
				}
			}
		}
		return true;
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

		if (noOfTilesTurned == 0) {
			noOfTilesTurned = 1;
			this.lastTile = selectedTile;
		} else if (noOfTilesTurned == 1) {
			noOfTilesTurned = 0;
			boolean matches = selectedTile.isMatch(lastTile);
			if (!matches) {
				turnTilesBack(row, column, selectedTile);
			}
			publishGameResult(matches, selectedTile);
			
			if (isGameOver()) {
				observers.forEach(t-> t.gameOver());
			}
		}
		
	}

	private void publishGameResult(boolean matches, Tile selectedTile) {
		observers.forEach(t -> t.gameTurnResult(matches, selectedTile, lastTile));
	}

	private void turnTilesBack(int row, int column, Tile selectedTile) {
		lastTile.turnFaceDown();
		selectedTile.turnFaceDown();
//		publish(getRowIndexOf(lastTile), getColumnIndexOf(lastTile), lastTile);
//		publish(row, column, selectedTile);
	}

	int getRowIndexOf(Tile tile) {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col] == tile) {
					return row;
				}
			}
		}

		throw new IllegalStateException("No Tile found");
	}

	int getColumnIndexOf(Tile tile) {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col] == tile) {
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
	public void register(GameObserver modelObserver) {
		observers.add(modelObserver);
	}

}
