package board;

import player.Player;

/**
 * Created by Student on 2/10/2016.
 */
public class VirtualBoard extends Board {

	public int slot;
	public int branch_depth;
	public int branch_score;

	//Constructor for VirtualBoard
	public VirtualBoard(int rows, int cols, int winLength, Player player1, Player player2, Player player, int slot,
						int branch_depth, int[][] pieces) {
		super(rows, cols, winLength, player1, player2);
		super.player = player;
		this.slot = slot;
		this.branch_depth = branch_depth;
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				this.pieces[y][x] = pieces[y][x];
			}
		}
	}

	//Boolean Place, changes board if possible and returns whether or not the move was valid
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
		if (place(slot, player)) {
			if (checkForWinner()) {
				return branch_score;
			} else {
				for (int i = 0; i < cols; i++) {
					VirtualBoard vboard = new VirtualBoard(rows, cols, winLength, player2,
							player2, player, i, branch_depth + 1, pieces);
					if (vboard.move() % 100 == 0) {
						branch_score += vboard.branch_score;
					}
				}
			}
		} else {
			branch_score = -1;
		}
		return branch_score;
	}

	//Check for a winner after placing, adjust branch_score accordingly
	private boolean checkForWinner() {
		int winnerInRows = rules.checkRows();
		if (winnerInRows != 0) {
			if (branch_depth % 2 == 0) {
				if (player.symbol == winnerInRows) {
					branch_score = branch_score + 100;
					return true;
				} else {
					branch_score = branch_score - 100;
					return true;
				}
			} else {
				if (player.symbol == winnerInRows) {
					branch_score = branch_score - 100;
					return true;
				} else {
					branch_score = branch_score + 100;
					return true;
				}
			}
		}
		int winnerInCols = rules.checkColumns();
		if (winnerInCols != 0) {
			if (branch_depth % 2 == 0) {
				if (player.symbol == winnerInCols) {
					branch_score = branch_score + 100;
					return true;
				} else {
					branch_score = branch_score - 100;
					return true;
				}
			} else {
				if (player.symbol == winnerInCols) {
					branch_score = branch_score - 100;
					return true;
				} else {
					branch_score = branch_score + 100;
					return true;
				}
			}
		}
		if (rows >= winLength && cols >= winLength) {
			int winnerInBLtTRDiagonals = rules.checkBLtTRDiagonals();
			if (winnerInBLtTRDiagonals != 0) {
				if (branch_depth % 2 == 0) {
					if (player.symbol == winnerInBLtTRDiagonals) {
						branch_score = branch_score + 100;
						return true;
					} else {
						branch_score = branch_score - 100;
						return true;
					}
				} else {
					if (player.symbol == winnerInBLtTRDiagonals) {
						branch_score = branch_score - 100;
						return true;
					} else {
						branch_score = branch_score + 100;
						return true;
					}
				}
			}
			int winnerInBRtTLDiagonals = rules.checkBRtTLDiagonals();
			if (winnerInBRtTLDiagonals != 0) {
				if (branch_depth % 2 == 0) {
					if (player.symbol == winnerInBRtTLDiagonals) {
						branch_score = branch_score + 100;
						return true;
					} else {
						branch_score = branch_score - 100;
						return true;
					}
				} else {
					if (player.symbol == winnerInBRtTLDiagonals) {
						branch_score = branch_score - 100;
						return true;
					} else {
						branch_score = branch_score + 100;
						return true;
					}
				}
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