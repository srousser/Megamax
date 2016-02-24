package board;

import input.Mouse;

public class Grid {

	public int width, height;
	public int cols;
	public int horizontalSlotSpacer;
	public int verticalSlotSpacer;
	public int slotDiam;
	public Quad[] slots;
	public Quad active;
	public Quad clicked;

	public Grid(int width, int height, int rows, int cols, int horizontalSlotSpacer) {
		this.width = width;
		this.height = height;
		this.cols = cols;
		this.horizontalSlotSpacer = horizontalSlotSpacer;
		slotDiam = (int) ((width - horizontalSlotSpacer * (cols + 1)) / (double) cols);
		verticalSlotSpacer = (int) ((double) (height - slotDiam * rows) / (rows + 1));
		slots = new Quad[cols];
		for (int x = 0; x < cols; x++) {
			slots[x] = new Quad(x, x * slotDiam + (x + 1) * horizontalSlotSpacer - horizontalSlotSpacer / 2, verticalSlotSpacer / 2, slotDiam + horizontalSlotSpacer, height - verticalSlotSpacer);
		}
	}

	public void update(Mouse mouse) {
			for (int x = 0; x < cols; x++) {
				if (slots[x].contains(mouse.mousex)) {
					active = slots[x];
					if (mouse.clicked) {
						clicked = slots[x];
						mouse.clicked = false;
					}
				}
			}
	}

}