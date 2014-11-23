package se.lina.model;

public class Tile {

	private String value;
	boolean isFaceUp;

	public Tile(String value) {
		this.value = value;
		isFaceUp = false;
	}

	public void turnFaceUp() {
		isFaceUp = true;
	}

	public void turnFaceDown() {
		isFaceUp = false;
	}

	public boolean isFaceUp() {
		return isFaceUp;
	}

	public boolean isMatch(Tile tile) {
		if (this.value.equals(tile.value)) {
			return true;
		}
		return false;
	}

	public String getValue() {
		return value;
	}
}
