package se.lina.view;

import org.junit.Before;
import org.junit.Test;

import se.lina.controller.MemoryBoardController;
import se.lina.model.MemoryBoard;
import se.lina.players.Players;

public class MainWindowTest {

	private MemoryBoardController controller;
	private Players players;

	@Before
	public void startUp(){

		MemoryBoard memoryBoard = new MemoryBoard(2, 2);
		players = new Players();
		controller = new MemoryBoardController(
				memoryBoard, players);
	}
	
	@Test(timeout = 1000)
	public void createMainWindowTimingTest(){
		MainWindow mainWindow = new MainWindow(controller, players, 2, 2);

	}
	
}
