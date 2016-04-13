package player;

import board.Board;
import board.LimVirtualBoard;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Andriy on 3/16/16.
 */
public class LimMinimaxAIPlayer extends Player {

	public Board board;
	public Random random = new Random();

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
		ArrayList<LimVirtualBoard> branches = new ArrayList<>();
		for (int i = 0; i < board.cols; i++) {
			LimVirtualBoard limVBoard = new LimVirtualBoard(board.rows, board.cols, board.winLength, board.player1,
					board.player2, board.activePlayer, i, 0, board.pieces, depthLimiter);
			if (limVBoard.move() % limVBoard.scoreMod == 0) {
				branches.add(limVBoard);
			}
			if (limVBoard.immediate) {
				return limVBoard.slot;
			}
		}
		int maxScore = branches.get(0).branchScore;
		int choiceSlot = branches.get(0).slot;
		for (LimVirtualBoard l : branches) {
			if (l.branchScore != maxScore) {
				break;
			}
		}
		for (int i = 0; i < branches.size(); i++) {
			if (branches.get(i).branchScore != maxScore) {
				break;
			}
			if (i == branches.size() - 1) {
				return branches.get(random.nextInt(branches.size())).slot;
			}
		}
		for (LimVirtualBoard l : branches) {
			if (l.branchScore >= maxScore) {
				maxScore = l.branchScore;
				choiceSlot = l.slot;
			}
		}
		return choiceSlot;
	}

}