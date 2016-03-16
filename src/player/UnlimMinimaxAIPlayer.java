package player;

import board.Board;
import board.UnlimVirtualBoard;
import util.Sort;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Andriy on 3/16/16.
 */
public class UnlimMinimaxAIPlayer extends Player {

	public Board board;
	public Random random = new Random();

	public UnlimMinimaxAIPlayer(String name, int symbol) {
		this.name = name;
		this.symbol = symbol;
	}

	public void update(Board board) {
		this.board = board;
		if (active) {
			move = unlimMinimax();
			moveMade = true;
		}
	}

	public void reset() {
		active = false;
		moveMade = false;
		move = 0;
	}

	public int unlimMinimax() {
		ArrayList<UnlimVirtualBoard> unlimVBoards = new ArrayList<UnlimVirtualBoard>();
		for (int i = 0; i < board.cols; i++) {
			UnlimVirtualBoard unlimVBoard = new UnlimVirtualBoard(board.rows, board.cols, board.winLength, board.player1,
					board.player2, board.activePlayer, i, 0, board.pieces);
			System.out.println(System.nanoTime());
			unlimVBoards.add(unlimVBoard);
			if (unlimVBoard.immediate) {
				return unlimVBoard.slot;
			}
		}
		if (Sort.areAllUnlimBranchScoresEqual(unlimVBoards)) {
			UnlimVirtualBoard u = unlimVBoards.get(random.nextInt(unlimVBoards.size()));
			System.out.println("Top Score: " + u.branchScore);
			return u.slot;
		} else {
			Sort.quickSortUnlimVBoardsByBranchScore(unlimVBoards, 0, unlimVBoards.size() - 1);
			try {
				System.out.println("Top Score: " + unlimVBoards.get(0).branchScore);
			} catch (IndexOutOfBoundsException e) {
				System.exit(0);
			}
			return unlimVBoards.get(0).slot;
		}
	}

}