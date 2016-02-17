package board;

import player.Player;

public class Board {

	public int rows, cols;
	public int winLength;
	public int[][] pieces;
	public Player player1;
	public Player player2;
	public Player activePlayer;
	protected Rules rules;

	public Board(int rows, int cols, int winLength, Player player1, Player player2) {
		this.rows = rows;
		this.cols = cols;
		pieces = new int[rows][cols];
		this.winLength = winLength;
		this.player1 = player1;
		this.player2 = player2;
		activePlayer = player1;
		player1.active = true;
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				pieces[y][x] = 0;
			}
		}
		rules = new Rules(this);
	}

	public void update() {
		if (activePlayer == null) {
			System.out.println("No activePlayer was set to active, you fucked up!");
		} else {
			if (activePlayer.movemade) {
				if (place(activePlayer.move, activePlayer)) {
					if (activePlayer == player1) {
						player1.active = false;
						player2.active = true;
						activePlayer = player2;
						player1.movemade = false;
					} else if (activePlayer == player2) {
						player2.active = false;
						player1.active = true;
						activePlayer = player1;
						player2.movemade = false;
					}
					System.out.println(checkForWinner());
				} else {
					activePlayer.movemade = false;
				}
			}
		}
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

	private String checkForWinner() {
		int winnerInRows = rules.checkRows();
		if (winnerInRows != 0) {
			return "Game over! Player with symbol " + winnerInRows + " won!";
		}
		int winnerInCols = rules.checkColumns();
		if (winnerInCols != 0) {
			return "Game over! Player with symbol " + winnerInCols + " won!";
		}
		if (rows >= winLength && cols >= winLength) {
			int winnerInBLtTRDiagonals = rules.checkBLtTRDiagonals();
			if (winnerInBLtTRDiagonals != 0) {
				return "Game over! Player with symbol " + winnerInBLtTRDiagonals + " won!";
			}
			int winnerInBRtTLDiagonals = rules.checkBRtTLDiagonals();
			if (winnerInBRtTLDiagonals != 0) {
				return "Game over! Player with symbol " + winnerInBRtTLDiagonals + " won!";
			}
		}
		//check for a draw
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (pieces[row][col] == 0) {
					return "Game not over yet.";
				}
			}
		}
		return "The game ended in a draw! You dipshits...";
	}
}