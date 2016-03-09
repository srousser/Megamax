package util;

import board.RandomBoard;
import board.VirtualBoard;

import java.util.ArrayList;

/**
 * Created by Andriy on 2/10/16.
 */
public class Sort {

	public static void quickSortVBoardsByBranchScore(ArrayList<VirtualBoard> a, int p, int r) {
		if (p < r) {
			int q = partitionVBoards(a, p, r);
			quickSortVBoardsByBranchScore(a, p, q - 1);
			quickSortVBoardsByBranchScore(a, q + 1, r);
		}
	}

	private static int partitionVBoards(ArrayList<VirtualBoard> a, int p, int r) {
		int x = a.get(r).branchScore;
		int i = p - 1;
		for (int j = p; j < r; j++) {
			if (a.get(j).branchScore > x) {
				i++;
				VirtualBoard temp = a.get(i);
				a.set(i, a.get(j));
				a.set(j, temp);
			}
		}
		VirtualBoard temp = a.get(i + 1);
		a.set(i + 1, a.get(r));
		a.set(r, temp);
		return i + 1;
	}

	public static void quickSortRBoardsByBranchScore(ArrayList<RandomBoard> a, int p, int r) {
		if (p < r) {
			int q = partitionRBoards(a, p, r);
			quickSortRBoardsByBranchScore(a, p, q - 1);
			quickSortRBoardsByBranchScore(a, q + 1, r);
		}
	}

	private static int partitionRBoards(ArrayList<RandomBoard> a, int p, int r) {
		int x = a.get(r).branchScore;
		int i = p - 1;
		for (int j = p; j < r; j++) {
			if (a.get(j).branchScore > x) {
				i++;
				RandomBoard temp = a.get(i);
				a.set(i, a.get(j));
				a.set(j, temp);
			}
		}
		RandomBoard temp = a.get(i + 1);
		a.set(i + 1, a.get(r));
		a.set(r, temp);
		return i + 1;
	}

}