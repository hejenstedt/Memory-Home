package se.lina.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import se.lina.model.MemoryBoard;
import se.lina.model.Tile;

public class MemoryBoardController {
	
	private MemoryBoard memoryModel;
	private ExecutorService service;
	
	
	public MemoryBoardController(MemoryBoard memoryModel) {
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

}
