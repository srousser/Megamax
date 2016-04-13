package player;

import board.Board;
import board.LimVirtualBoard;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Andriy on 3/16/16.
 */
public class LimMinimaxAIPlayer extends Player {

	private Random random = new Random();
	private int turnCount;

	public LimMinimaxAIPlayer(String name, int symbol, int depthLimiter) {
		this.name = name;
		this.symbol = symbol;
		this.depthLimiter = depthLimiter;
		turnCount = 0;
	}

	public void update(Board board) {
		if (active) {
//			if (turnCount == 0) {
//				long tInit = System.nanoTime();
//				move = limMinimax(board);
//				long tFinal = System.nanoTime();
//				long dT = tFinal - tInit;
//				try {
//					FileWriter writer = new FileWriter("depth_" + depthLimiter + "_minimax_time_output.txt", true);
//					writer.write(dT + "\r\n");
//					writer.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				turnCount++;
//			} else {
			move = limMinimax(board);
//			}
			moveMade = true;
		}
	}

	public void reset() {
		active = false;
		moveMade = false;
		move = 0;
		turnCount = 0;
	}

	public int limMinimax(Board board) {
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