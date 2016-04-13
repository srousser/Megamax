package player;

import board.Board;
import board.RandomVirtualBoard;
import util.BranchVector;

import java.util.ArrayList;

/**
 * Created by Andriy on 2/10/16.
 */
public class RandomSampleAIPlayer extends Player {

	public RandomSampleAIPlayer(String name, int symbol, int depthLimiter, int samplesLimiter) {
		this.name = name;
		this.symbol = symbol;
		this.depthLimiter = depthLimiter;
		this.samplesLimiter = samplesLimiter;
	}

	public void update(Board board) {
		if (active) {
			move = randomSample(board);
			moveMade = true;
		}
	}

	public void reset() {
		active = false;
		moveMade = false;
		move = 0;
	}

	public int randomSample(Board board) {
		ArrayList<BranchVector> branches = new ArrayList<>();
		for (int i = 0; i < board.cols; i++) {
			branches.add(i, new BranchVector(0, i, true));
			for (int j = 0; j < samplesLimiter; j++) {
				RandomVirtualBoard rboard = new RandomVirtualBoard(board.rows, board.cols, board.winLength, board.player1,
						board.player2, board.activePlayer, i, 0, board.pieces, depthLimiter);
				if (rboard.move() % 100 == 0) {
					branches.get(i).branchScore += rboard.branchScore;
				} else {
					branches.get(i).valid = false;
					break;
				}
				if (rboard.immediate) {
					return rboard.slot;
				}
			}
		}
//		for (int i = 0; i < branches.size(); i++) {
//			if (!branches.get(i).valid) {
//				branches.remove(i);
//			}
//		}
		ArrayList<BranchVector> validBranches = new ArrayList<>();
		for (BranchVector v : branches) {
			if (v.valid) {
				validBranches.add(v);
			}
		}
//		System.out.println(validBranches.size());
		int maxScore = validBranches.get(0).branchScore;
		int choiceSlot = validBranches.get(0).slot;
		for (BranchVector b : validBranches) {
			if (b.branchScore >= maxScore) {
				maxScore = b.branchScore;
				choiceSlot = b.slot;
			}
		}
		return choiceSlot;
	}
}