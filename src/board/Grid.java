package board;

import input.Mouse;

public class Grid {

	public int width, height;
	public int rows, cols;
	public int horizontalSlotSpacer;
	public int verticalSlotSpacer;
	public int slotDiam;
	public Quad[][] slots;
	public Quad active;
	public Quad clicked;

	public Grid(int width, int height, int rows, int cols, int horizontalSlotSpacer) {
		this.width = width;
		this.height = height;
		this.rows = rows;
		this.cols = cols;
		this.horizontalSlotSpacer = horizontalSlotSpacer;
		slotDiam = (int) ((width - horizontalSlotSpacer * (cols + 1)) / (double) cols);
		verticalSlotSpacer = (int) ((double) (height - slotDiam * rows) / (rows + 1));
		slots = new Quad[rows][cols];
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				slots[y][x] = new Quad(x, y, x * slotDiam + (x + 1) * horizontalSlotSpacer - horizontalSlotSpacer / 2, y * slotDiam + (y + 1) * verticalSlotSpacer - verticalSlotSpacer / 2, slotDiam + horizontalSlotSpacer, slotDiam + verticalSlotSpacer);
			}
		}
	}

	public void update(Mouse mouse) {
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				if (slots[y][x].contains(mouse.mousex, mouse.mousey)) {
					active = slots[y][x];
					if (mouse.clicked) {
						clicked = slots[y][x];
						mouse.clicked = false;
					}
				}
			}
		}
	}

}