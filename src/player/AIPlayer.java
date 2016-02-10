package player;

import board.Board;
import util.Sort;

/**
 * Created by Andriy on 2/10/16.
 */
public class AIPlayer extends Player {

	public Board board;

	public AIPlayer(String name, int symbol, Board board) {
		this.name = name;
		this.symbol = symbol;
		this.board = board;
	}

	public void update() {
		if (active) {
			move = minimax();
			movemade = true;
		}
	}

	public int minimax() {
		ArrayList<VirtualBoard> vboards = new ArrayList<VirtualBoard>();
		for (int i = 0; i < cols; i++) {
			VirtualBoard vboard = new VirtualBoard(board.rows, board.cols, board.winLength, board.player2,
					board.player2, board.player, i, 0, board.pieces);
			if (vboard.move() % 100 == 0) {
				vboards.add(vboard);
			}
		}
		Sort.quickSort(vboards, 0, vboards.size() - 1);
		return vboards.get(0).slot;
	}

}