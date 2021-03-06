package board;

import player.Player;

import java.util.Random;

/**
 * Created by Andriy on 2/26/16.
 */
public class RandomVirtualBoard extends Board {

	public int slot;
	public int branchDepth;
	public int branchScore;
	public int depthLimiter;
	public int scoreMod;
	public boolean immediate;
	public Random random = new Random();

	public RandomVirtualBoard(int rows, int cols, int winLength, Player player1, Player player2, Player activePlayer, int slot,
							  int branchDepth, int[][] pieces, int depthLimiter) {
		super(rows, cols, winLength, player1, player2, false);
		super.activePlayer = activePlayer;
		this.slot = slot;
		this.branchDepth = branchDepth;
		this.depthLimiter = depthLimiter;
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				this.pieces[y][x] = pieces[y][x];
			}
		}
		scoreMod = (depthLimiter - branchDepth) * 100;
//		scoreMod = 100;
	}

	public boolean place(int slot, Player player) {
		if (pieces[0][slot] == 0) {
			for (int i = rows - 1; i >= 0; i--) {
				if (pieces[i][slot] == 0) {
					pieces[i][slot] = player.symbol;
					break;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public int move() {
		if (place(slot, activePlayer)) {
			if (checkForWinner()) {
				if (branchDepth == 0) {
					immediate = true;
				}
				return branchScore;
			} else {
				if (activePlayer == player1) {
					activePlayer = player2;
				} else if (activePlayer == player2) {
					activePlayer = player1;
				}
				if (branchDepth + 1 < depthLimiter) {
					RandomVirtualBoard rboard = new RandomVirtualBoard(rows, cols, winLength, player1,
							player2, activePlayer, random.nextInt(cols), branchDepth + 1, pieces, depthLimiter);
					if (rboard.move() % 100 == 0) {
						branchScore += rboard.branchScore;
					}
				}
			}
		} else {
			branchScore = -1;
		}
		return branchScore;
	}

	private boolean checkForWinner() {
		int winnerInRows = rules.checkRows();
		if (winnerInRows != 0) { //if there is a winner
			if (branchDepth % 2 == 0) { //bot's turn
				branchScore += scoreMod;
			} else { //simulated player's turn
				branchScore -= scoreMod;
			}
			return true;
		}
		int winnerInCols = rules.checkColumns();
		if (winnerInCols != 0) {
			if (branchDepth % 2 == 0) {
				branchScore += scoreMod;
			} else {
				branchScore -= scoreMod;
			}
			return true;
		}
		if (rows >= winLength && cols >= winLength) {
			int winnerInBLtTRDiagonals = rules.checkBLtTRDiagonals();
			if (winnerInBLtTRDiagonals != 0) {
				if (branchDepth % 2 == 0) {
					branchScore += scoreMod;
				} else {
					branchScore -= scoreMod;
				}
				return true;
			}
			int winnerInBRtTLDiagonals = rules.checkBRtTLDiagonals();
			if (winnerInBRtTLDiagonals != 0) {
				if (branchDepth % 2 == 0) {
					branchScore += scoreMod;
				} else {
					branchScore -= scoreMod;
				}
				return true;
			}
		}
		//check for a draw
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (pieces[row][col] == 0) {
					return false;
				}
			}
		}
		return true;
	}

}