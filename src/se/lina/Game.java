package se.lina;

import javax.swing.SwingUtilities;

import se.lina.controller.MemoryBoardController;
import se.lina.model.MemoryBoard;
import se.lina.players.Players;
import se.lina.view.MainWindow;

public class Game {

	private MainWindow mainWindow;

	public static void main(String[] args) {
		Game game = new Game();
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				game.startGame();
			}
		});
		
	}

	void startGame(){
		MemoryBoard memoryBoard = new MemoryBoard(4, 4);
		Players players = new Players();
		MemoryBoardController controller = new MemoryBoardController(
				memoryBoard, players);
		mainWindow = new MainWindow(controller, players);
		memoryBoard.register(mainWindow);
		memoryBoard.register(players);
		players.register(mainWindow);
	}
	
	public Game() {

	}

}
