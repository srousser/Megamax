package player;

import board.Board;
import board.RandomBoard;
import board.VirtualBoard;
import util.Sort;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Andriy on 2/10/16.
 */
public class AIPlayer extends Player {

	public Board board;
	public Random random = new Random();

	public AIPlayer(String name, int symbol) {
		this.name = name;
		this.symbol = symbol;
	}

	public void update(Board board) {
		this.board = board;
		if (active) {
//			move = randomSample();
			move = minimax();
			moveMade = true;
		}
	}

	public void reset() {
		active = false;
		moveMade = false;
		move = 0;
	}

	public int minimax() {
		ArrayList<VirtualBoard> vboards = new ArrayList<VirtualBoard>();
		for (int i = 0; i < board.cols; i++) {
			VirtualBoard vboard = new VirtualBoard(board.rows, board.cols, board.winLength, board.player1,
					board.player2, board.activePlayer, i, 0, board.pieces, 6);
			System.out.println(System.nanoTime());
			if (vboard.move() % 100 == 0) {
				vboards.add(vboard);
			}
			if (vboard.immediate) {
				return vboard.slot;
			}
		}
		Sort.quickSortVBoardsByBranchScore(vboards, 0, vboards.size() - 1);
		System.out.println("Score: " + vboards.get(0).branchScore);
		return vboards.get(0).slot;
	}

	public int randomSample() {
		ArrayList<RandomBoard> rboards = new ArrayList<RandomBoard>();
		for (int i = 0; i < 1000; i++) {
			RandomBoard rboard = new RandomBoard(board.rows, board.cols, board.winLength, board.player1,
					board.player2, board.activePlayer, random.nextInt(board.cols), 0, board.pieces, 20);
			System.out.println(System.nanoTime());
			if (rboard.move() % 100 == 0) {
				rboards.add(rboard);
			}
			if (rboard.immediate) {
				return rboard.slot;
			}
		}
		Sort.quickSortRBoardsByBranchScore(rboards, 0, rboards.size() - 1);
		System.out.println("Score: " + rboards.get(0).branchScore);
		return rboards.get(0).slot;
	}
}