package board;

public class Rules {

	private Board board;

	public Rules(Board board) {
		this.board = board;
	}

	public int checkRows() {
		for (int row = 0; row < board.rows; row++) {
			int streak = 0;
			for (int col = 0; col < board.cols; col++) {
				if (col == 0) {
					if (board.pieces[row][col] != 0) {
						streak++;
					}
				} else {
					if (board.pieces[row][col] == board.pieces[row][col - 1]) {
						if (board.pieces[row][col] != 0) {
							streak++;
						}
					} else {
						streak = board.pieces[row][col] != 0 ? 1 : 0;
					}
				}
				if (streak >= board.winLength) {
					return board.pieces[row][col];
				}
			}
		}
		return 0;
	}

	public int checkColumns() {
		for (int col = 0; col < board.cols; col++) {
			int streak = 0;
			for (int row = 0; row < board.rows; row++) {
				if (row == 0) {
					if (board.pieces[row][col] != 0) {
						streak++;
					}
				} else {
					if (board.pieces[row][col] == board.pieces[row - 1][col]) {
						if (board.pieces[row][col] != 0) {
							streak++;
						}
					} else {
						streak = board.pieces[row][col] != 0 ? 1 : 0;
					}
				}
				if (streak >= board.winLength) {
					return board.pieces[row][col];
				}
			}
		}
		return 0;
	}

	/*
	Checks for wins from any symbol in diagonals, going from bottom left to top right.
	@return The symbol of the winning player; either 1 or -1.
	Returns 0 to indicate that there is no winner in any diagonals of this type.
	 */
	public int checkBLtTRDiagonals() {
		for (int row = board.winLength - 1; row < board.rows; row++) {
			int streak = 0;
			int prev = 0;
			int limitingFactor = row + 1 < board.cols ? row + 1 : board.cols;
			for (int i = 0; i < limitingFactor; i++) {
				int current = board.pieces[row - i][i];
				if (current != 0) {
					if (current == prev) {
						streak++;
					} else {
						streak = 1;
					}
				} else {
					streak = 0;
				}
				prev = current;
				if (streak >= board.winLength) {
					return current;
				}
			}
		}
		for (int col = 0; col <= board.cols - board.winLength; col++) {
			int streak = 0;
			int prev = 0;
			int limitingFactor = board.rows < board.cols - col ? board.rows : board.cols - col;
			for (int i = 0; i < limitingFactor; i++) {
				int current = board.pieces[board.rows - 1 - i][col + i];
				if (current != 0) {
					if (current == prev) {
						streak++;
					} else {
						streak = 1;
					}
				} else {
					streak = 0;
				}
				prev = current;
				if (streak >= board.winLength) {
					return current;
				}
			}
		}
		return 0;
	}

	public int checkBRtTLDiagonals() {
		for (int row = board.winLength - 1; row < board.rows; row++) {
			int streak = 0;
			int prev = 0;
			int limitingFactor = row + 1 < board.cols ? row + 1 : board.cols;
			for (int i = 0; i < limitingFactor; i++) {
				int current = board.pieces[row - i][board.cols - 1 - i];
				if (current != 0) {
					if (current == prev) {
						streak++;
					} else {
						streak = 1;
					}
				} else {
					streak = 0;
				}
				prev = current;
				if (streak >= board.winLength) {
					return current;
				}
			}
		}
		for (int col = board.winLength - 1; col < board.cols; col++) {
			int streak = 0;
			int prev = 0;
			int limitingFactor = board.rows < col + 1 ? board.rows : col + 1;
			for (int i = 0; i < limitingFactor; i++) {
				int current = board.pieces[board.rows - 1 - i][col - i];
				if (current != 0) {
					if (current == prev) {
						streak++;
					} else {
						streak = 1;
					}
				} else {
					streak = 0;
				}
				prev = current;
				if (streak >= board.winLength) {
					return current;
				}
			}
		}
		return 0;
	}
}