package player;

import board.Grid;

public abstract class Player {

	public String name;
	public int symbol;
	public boolean active = false;
	public boolean movemade;
	public int move;
}