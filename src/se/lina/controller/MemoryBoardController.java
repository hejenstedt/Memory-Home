package se.lina.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import se.lina.model.MemoryBoard;
import se.lina.model.Tile;
import se.lina.players.Player;
import se.lina.players.Players;

public class MemoryBoardController {
	
	private MemoryBoard memoryModel;
	private ExecutorService service;
	private Players players;
	
	public MemoryBoardController(MemoryBoard memoryModel, Players players) {
		this.players= players;
		this.memoryModel= memoryModel;
		service = Executors.newSingleThreadExecutor();
	}

	public void tileSelected(int row, int column) {
		service.execute(new Runnable(){
			public void run(){
				memoryModel.turnTileUp(row, column);
			}
		});
	}
	
	public void newPlayerAdded(String name){
		players.addPlayer(new Player(name));
	}
}
