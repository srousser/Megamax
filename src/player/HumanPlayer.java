package player;

import board.Grid;

/**
 * Created by Student on 2/10/2016.
 */
public class HumanPlayer extends Player {
	public Grid grid;

	public HumanPlayer(String name, int symbol) {
		this.name = name;
		this.symbol = symbol;
	}

	public void update(Grid grid) {
		if (active) {
			if (grid.clicked != null) {
				move = grid.clicked.col;
				moveMade = true;
				grid.clicked = null;
			}
		}
	}
}