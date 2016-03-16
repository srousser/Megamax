package player;

import board.Board;
import board.LimVirtualBoard;
import util.Sort;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Andriy on 3/16/16.
 */
public class LimMinimaxAIPlayer extends Player {

	public Board board;
	public Random random = new Random();
	private int depthLimiter;

	public LimMinimaxAIPlayer(String name, int symbol, int depthLimiter) {
		this.name = name;
		this.symbol = symbol;
		this.depthLimiter = depthLimiter;
	}

	public void update(Board board) {
		this.board = board;
		if (active) {
			move = limMinimax();
			moveMade = true;
		}
	}

	public void reset() {
		active = false;
		moveMade = false;
		move = 0;
	}

	public int limMinimax() {
		ArrayList<LimVirtualBoard> limVBoards = new ArrayList<LimVirtualBoard>();
		for (int i = 0; i < board.cols; i++) {
			LimVirtualBoard limVBoard = new LimVirtualBoard(board.rows, board.cols, board.winLength, board.player1,
					board.player2, board.activePlayer, i, 0, board.pieces, depthLimiter);
			System.out.println(System.nanoTime());
			if (limVBoard.move() % 100 == 0) {
				limVBoards.add(limVBoard);
			}
			if (limVBoard.immediate) {
				return limVBoard.slot;
			}
		}
		if (Sort.areAllLimBranchScoresEqual(limVBoards)) {
			LimVirtualBoard l = limVBoards.get(random.nextInt(limVBoards.size()));
			System.out.println("Top Score: " + l.branchScore);
			return l.slot;
		} else {
			Sort.quickSortLimVBoardsByBranchScore(limVBoards, 0, limVBoards.size() - 1);
			try {
				System.out.println("Top Score: " + limVBoards.get(0).branchScore);
			} catch (IndexOutOfBoundsException e) {
				System.exit(0);
			}
			return limVBoards.get(0).slot;
		}
	}

}
