package se.lina;

import se.lina.controller.MemoryBoardController;
import se.lina.model.MemoryBoard;
import se.lina.players.PlayerController;
import se.lina.view.MainWindow;

public class Game {

	private MainWindow mainWindow;

	public static void main(String[] args) {
		Game game = new Game();

	}

	public Game() {
		MemoryBoard memoryBoard = new MemoryBoard(4, 4);
		MemoryBoardController controller = new MemoryBoardController(
				memoryBoard);
		mainWindow = new MainWindow(controller);
		memoryBoard.register(mainWindow);
		PlayerController playerController = new PlayerController();
		memoryBoard.register(playerController);

	}

}
