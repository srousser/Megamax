package util;

import board.VirtualBoard;

import java.util.ArrayList;

/**
 * Created by Andriy on 2/10/16.
 */
public class Sort {

	public static void quickSort(ArrayList<VirtualBoard> a, int p, int r) {
		if (p < r) {
			int q = partition(a, p, r);
			quickSort(a, p, q - 1);
			quickSort(a, q + 1, r);
		}
	}

	private static int partition(ArrayList<VirtualBoard> a, int p, int r) {
		int x = a.get(r).branch_score;
		int i = p - 1;
		for (int j = p; j < r; j++) {
			if (a.get(j).branch_score > x) {
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
}