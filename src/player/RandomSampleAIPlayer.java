package player;

import board.Board;
import board.RandomBoard;
import util.Sort;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Andriy on 2/10/16.
 */
public class RandomSampleAIPlayer extends Player {

	public Board board;
	public Random random = new Random();

	public RandomSampleAIPlayer(String name, int symbol) {
		this.name = name;
		this.symbol = symbol;
	}

	public void update(Board board) {
		this.board = board;
		if (active) {
			move = randomSample();
			moveMade = true;
		}
	}

	public void reset() {
		active = false;
		moveMade = false;
		move = 0;
	}

	public int randomSample() {
		ArrayList<RandomBoard> rboards = new ArrayList<RandomBoard>();
		for (int i = 0; i < 10000; i++) {
			RandomBoard rboard = new RandomBoard(board.rows, board.cols, board.winLength, board.player1,
					board.player2, board.activePlayer, random.nextInt(board.cols), 0, board.pieces, 20);
//			System.out.println(System.nanoTime());
			if (rboard.move() % 100 == 0) {
				rboards.add(rboard);
			}
			if (rboard.immediate) {
				return rboard.slot;
			}
		}
		Sort.quickSortRBoardsByBranchScore(rboards, 0, rboards.size() - 1);
		System.out.println("Top Score: " + rboards.get(0).branchScore);
		return rboards.get(0).slot;
	}
}