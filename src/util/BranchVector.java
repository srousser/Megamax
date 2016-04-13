package util;

/**
 * Created by Andriy on 4/12/16.
 */
public class BranchVector {

	public int branchScore;
	public int slot;
	public boolean valid;

	public BranchVector(int branchScore, int slot, boolean valid) {
		this.branchScore = branchScore;
		this.slot = slot;
		this.valid = valid;
	}

}
