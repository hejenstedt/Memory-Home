package se.lina.players;


public interface PlayerEventPublisher {

	void register(PlayerEventObserver observer);

	void publish(Player player);
	
}
