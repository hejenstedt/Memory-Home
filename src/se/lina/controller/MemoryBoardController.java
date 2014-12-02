package se.lina.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import se.lina.model.MemoryBoard;
import se.lina.players.Player;
import se.lina.players.Players;

public class MemoryBoardController {

	private MemoryBoard memoryBoard;
	private ExecutorService service;
	private Players players;
	private boolean playerAdded;

	public MemoryBoardController(MemoryBoard memoryModel, Players players) {
		this.players = players;
		this.memoryBoard = memoryModel;
		playerAdded = false;
		service = Executors.newSingleThreadExecutor();
	}

	public void tileSelected(int row, int column) {
		if (playerAdded) {
			service.execute(new Runnable() {
				public void run() {
					memoryBoard.turnTileUp(row, column);
				}
			});
		}
	}

	public void newPlayerAdded(String name) {
		playerAdded = true;
		players.addPlayer(new Player(name));
	}

	public void startNewGame() {
		memoryBoard.reStartBoard();
	}

	public void changeBoardSize(int rows, int columns) {
		memoryBoard.reStartBoardWithNewSize(rows, columns);
	}

}
