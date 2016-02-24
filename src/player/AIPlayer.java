package player;

import board.Board;
import board.VirtualBoard;
import util.Sort;

import java.util.ArrayList;

/**
 * Created by Andriy on 2/10/16.
 */
public class AIPlayer extends Player {

	public Board board;

	public AIPlayer(String name, int symbol) {
		this.name = name;
		this.symbol = symbol;
	}

	public void update(Board board) {
		this.board = board;
		if (active) {
			move = minimax();
			moveMade = true;
		}
	}

	public int minimax() {
		ArrayList<VirtualBoard> vboards = new ArrayList<VirtualBoard>();
		for (int i = 0; i < board.cols; i++) {
			VirtualBoard vboard = new VirtualBoard(board.rows, board.cols, board.winLength, board.player1,
					board.player2, board.activePlayer, i, 0, board.pieces, 2);
			System.out.println(System.nanoTime());
			if (vboard.move() % 100 == 0) {
				vboards.add(vboard);
			}
		}
		Sort.quickSort(vboards, 0, vboards.size() - 1);
		System.out.println("Score: " + vboards.get(0).branchScore);
		return vboards.get(0).slot;
	}
}