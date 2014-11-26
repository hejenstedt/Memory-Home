package se.lina.players;

import java.util.ArrayList;

public interface PlayerEventObserver {

	void playerSettingsChanged(ArrayList<Player> players);
	
}
