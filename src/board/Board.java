package board;

import player.LimMinimaxAIPlayer;
import player.Player;
import player.RandomSampleAIPlayer;

import java.io.FileWriter;
import java.io.IOException;

public class Board {

	public int rows, cols;
	public int winLength;
	public int[][] pieces;
	public Player player1;
	public Player player2;
	public Player activePlayer;
	protected Rules rules;
	protected boolean largeScaleTesting;

	public Board(int rows, int cols, int winLength, Player player1, Player player2, boolean largeScaleTesting) {
		this.rows = rows;
		this.cols = cols;
		pieces = new int[rows][cols];
		this.winLength = winLength;
		this.player1 = player1;
		this.player2 = player2;
		activePlayer = player1;
		player1.active = true;
		this.largeScaleTesting = largeScaleTesting;
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				pieces[y][x] = 0;
			}
		}
		rules = new Rules(this);
	}

	public void update() {
		if (activePlayer == null) {
			System.out.println("O no no active player rip");
		} else {
			if (activePlayer.moveMade) {
				if (place(activePlayer.move, activePlayer)) {
					if (activePlayer == player1) {
						player1.active = false;
						player2.active = true;
						activePlayer = player2;
						player1.moveMade = false;
					} else if (activePlayer == player2) {
						player2.active = false;
						player1.active = true;
						activePlayer = player1;
						player2.moveMade = false;
					}
					if (largeScaleTesting) {
						checkForWinner();
					} else {
						System.out.println(checkForWinner());
					}
				} else {
					activePlayer.moveMade = false;
				}
			}
		}
	}

	public void reset() {
		player1.reset();
		player2.reset();
		activePlayer = player1;
		player1.active = true;
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				pieces[y][x] = 0;
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
			if (player1.symbol == winnerInRows) {
				reset();
				if (player1 instanceof LimMinimaxAIPlayer) {
					try {
						FileWriter writer = new FileWriter("mimimax_testing_output.txt", true);
						writer.write(player1.depthLimiter + "\r\n");
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (player1 instanceof RandomSampleAIPlayer) {
					try {
						FileWriter writer = new FileWriter("random_sampling_testing_output.txt", true);
						writer.write(player1.samplesLimiter + "\r\n"); //use this when testing with depthLimiter constant
//						writer.write(player1.depthLimiter + "\r\n"); //use this when testing with samplesLimiter constant
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return (player1.name + " won! (Symbol: " + player1.symbol + ")");
			} else if (player2.symbol == winnerInRows) {
				reset();
				if (player2 instanceof LimMinimaxAIPlayer) {
					try {
						FileWriter writer = new FileWriter("mimimax_testing_output.txt", true);
						writer.write(player2.depthLimiter + "\r\n");
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (player2 instanceof RandomSampleAIPlayer) {
					try {
						FileWriter writer = new FileWriter("random_sampling_testing_output.txt", true);
						writer.write(player2.samplesLimiter + "\r\n"); //use this when testing with depthLimiter constant
//						writer.write(player2.depthLimiter + "\r\n"); //use this when testing with samplesLimiter constant
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return (player2.name + " won! (Symbol: " + player2.symbol + ")");
			}
		}
		int winnerInCols = rules.checkColumns();
		if (winnerInCols != 0) {
			if (player1.symbol == winnerInCols) {
				reset();
				if (player1 instanceof LimMinimaxAIPlayer) {
					try {
						FileWriter writer = new FileWriter("mimimax_testing_output.txt", true);
						writer.write(player1.depthLimiter + "\r\n");
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (player1 instanceof RandomSampleAIPlayer) {
					try {
						FileWriter writer = new FileWriter("random_sampling_testing_output.txt", true);
						writer.write(player1.samplesLimiter + "\r\n"); //use this when testing with depthLimiter constant
//						writer.write(player1.depthLimiter + "\r\n"); //use this when testing with samplesLimiter constant
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return (player1.name + " won! (Symbol: " + player1.symbol + ")");
			} else if (player2.symbol == winnerInCols) {
				reset();
				if (player2 instanceof LimMinimaxAIPlayer) {
					try {
						FileWriter writer = new FileWriter("mimimax_testing_output.txt", true);
						writer.write(player2.depthLimiter + "\r\n");
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (player2 instanceof RandomSampleAIPlayer) {
					try {
						FileWriter writer = new FileWriter("random_sampling_testing_output.txt", true);
						writer.write(player2.samplesLimiter + "\r\n"); //use this when testing with depthLimiter constant
//						writer.write(player2.depthLimiter + "\r\n"); //use this when testing with samplesLimiter constant
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return (player2.name + " won! (Symbol: " + player2.symbol + ")");
			}
		}
		if (rows >= winLength && cols >= winLength) {
			int winnerInBLtTRDiagonals = rules.checkBLtTRDiagonals();
			if (winnerInBLtTRDiagonals != 0) {
				if (player1.symbol == winnerInBLtTRDiagonals) {
					reset();
					if (player1 instanceof LimMinimaxAIPlayer) {
						try {
							FileWriter writer = new FileWriter("mimimax_testing_output.txt", true);
							writer.write(player1.depthLimiter + "\r\n");
							writer.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else if (player1 instanceof RandomSampleAIPlayer) {
						try {
							FileWriter writer = new FileWriter("random_sampling_testing_output.txt", true);
							writer.write(player1.samplesLimiter + "\r\n"); //use this when testing with depthLimiter constant
//							writer.write(player1.depthLimiter + "\r\n"); //use this when testing with samplesLimiter constant
							writer.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					return (player1.name + " won! (Symbol: " + player1.symbol + ")");
				} else if (player2.symbol == winnerInBLtTRDiagonals) {
					reset();
					if (player2 instanceof LimMinimaxAIPlayer) {
						try {
							FileWriter writer = new FileWriter("mimimax_testing_output.txt", true);
							writer.write(player2.depthLimiter + "\r\n");
							writer.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else if (player2 instanceof RandomSampleAIPlayer) {
						try {
							FileWriter writer = new FileWriter("random_sampling_testing_output.txt", true);
							writer.write(player2.samplesLimiter + "\r\n"); //use this when testing with depthLimiter constant
//							writer.write(player2.depthLimiter + "\r\n"); //use this when testing with samplesLimiter constant
							writer.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					return (player2.name + " won! (Symbol: " + player2.symbol + ")");
				}
			}
			int winnerInBRtTLDiagonals = rules.checkBRtTLDiagonals();
			if (winnerInBRtTLDiagonals != 0) {
				if (player1.symbol == winnerInBRtTLDiagonals) {
					reset();
					if (player1 instanceof LimMinimaxAIPlayer) {
						try {
							FileWriter writer = new FileWriter("mimimax_testing_output.txt", true);
							writer.write(player1.depthLimiter + "\r\n");
							writer.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else if (player1 instanceof RandomSampleAIPlayer) {
						try {
							FileWriter writer = new FileWriter("random_sampling_testing_output.txt", true);
							writer.write(player1.samplesLimiter + "\r\n"); //use this when testing with depthLimiter constant
//							writer.write(player1.depthLimiter + "\r\n"); //use this when testing with samplesLimiter constant
							writer.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					return (player1.name + " won! (Symbol: " + player1.symbol + ")");
				} else if (player2.symbol == winnerInBRtTLDiagonals) {
					reset();
					if (player2 instanceof LimMinimaxAIPlayer) {
						try {
							FileWriter writer = new FileWriter("mimimax_testing_output.txt", true);
							writer.write(player2.depthLimiter + "\r\n");
							writer.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else if (player2 instanceof RandomSampleAIPlayer) {
						try {
							FileWriter writer = new FileWriter("random_sampling_testing_output.txt", true);
							writer.write(player2.samplesLimiter + "\r\n"); //use this when testing with depthLimiter constant
//							writer.write(player2.depthLimiter + "\r\n"); //use this when testing with samplesLimiter constant
							writer.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					return (player2.name + " won! (Symbol: " + player2.symbol + ")");
				}
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
		reset();
		try {
			FileWriter writer = new FileWriter("mimimax_testing_output.txt", true);
			writer.write("draw\r\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileWriter writer = new FileWriter("random_sampling_testing_output.txt", true);
			writer.write("draw\r\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/* REMEMBER THAT WHEN TESTING RANDOM VS. LIMITED, DRAWS WILL BE DUPED -> ONE DRAW REGISTERS AS A DRAW IN BOTH TEXT FILES */
		return "The game ended in a draw!";
	}
}