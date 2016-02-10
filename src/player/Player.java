package player;

import board.Grid;

public abstract class Player {

	public String name;
	public int symbol;
	public boolean active;
	public boolean movemade;
	public int move;

	public Player() {
		name = "Player1";
		symbol = 1;
		active = false;
	}

	public Player(String name, int symbol) {
		this.name = name;
		this.symbol = symbol;
		active = false;
	}
	
}