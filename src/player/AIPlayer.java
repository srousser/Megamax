package player;

/**
 * Created by Andriy on 2/10/16.
 */
public class AIPlayer extends Player {

	public AIPlayer(String name, int symbol) {
		this.name = name;
		this.symbol = symbol;
	}

	public void update() {
		if (active) {
			move = minimax();
			movemade = true;
		}
	}

	public int minimax() {
		return 0;
	}

}