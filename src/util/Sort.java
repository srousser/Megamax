package util;

import board.UnlimVirtualBoard;

import java.util.ArrayList;

/**
 * Created by Andriy on 2/10/16.
 */
public class Sort {

	public static void quickSortUnlimVBoardsByBranchScore(ArrayList<UnlimVirtualBoard> a, int p, int r) {
		if (p < r) {
			int q = partitionUnlimVBoards(a, p, r);
			quickSortUnlimVBoardsByBranchScore(a, p, q - 1);
			quickSortUnlimVBoardsByBranchScore(a, q + 1, r);
		}
	}

	private static int partitionUnlimVBoards(ArrayList<UnlimVirtualBoard> a, int p, int r) {
		int x = a.get(r).branchScore;
		int i = p - 1;
		for (int j = p; j < r; j++) {
			if (a.get(j).branchScore > x) {
				i++;
				UnlimVirtualBoard temp = a.get(i);
				a.set(i, a.get(j));
				a.set(j, temp);
			}
		}
		UnlimVirtualBoard temp = a.get(i + 1);
		a.set(i + 1, a.get(r));
		a.set(r, temp);
		return i + 1;
	}

	public static boolean areAllUnlimBranchScoresEqual(ArrayList<UnlimVirtualBoard> unlimVBoards) {
		if (unlimVBoards.size() == 0) {
			return false;
		} else {
			double first = unlimVBoards.get(0).branchScore;
			for (UnlimVirtualBoard u : unlimVBoards) {
				if (u.branchScore != first) {
					return false;
				}
			}
			return true;
		}
	}

}