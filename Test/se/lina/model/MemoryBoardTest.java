package se.lina.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class MemoryBoardTest {

	@Test
	public void whenTileIsTurnedUp_ObserverRecievesMessage(){
		MemoryBoard memoryBoard = new MemoryBoard(2, 2);
		FakeObserver observer = new FakeObserver();
		memoryBoard.register(observer);
		
		memoryBoard.turnTileUp(0, 0);
		
		assertTrue(observer.hasRecievedTileTurnedMessage());
	}
	

	
}
