package display;

import board.Board;
import board.Quad;

import java.awt.*;

public class Render {

	private int width, height;
	private int rows, cols;
	public Graphics2D g2;
	public int horizontalSlotSpacer = 20;
	public int slotDiam;
	public int verticalSlotSpacer;

	public Render(int width, int height, int rows, int cols) {
		this.width = width;
		this.height = height;
		this.rows = rows;
		this.cols = cols;
		slotDiam = (int) ((width - horizontalSlotSpacer * (cols + 1)) / (double) cols);
		verticalSlotSpacer = (int) ((double) (height - slotDiam * rows) / (rows + 1));
	}

	public void clear() {
		g2.setColor(Color.darkGray);
		g2.fillRect(0, 0, width, height);
	}

	public void renderTri() {
		g2.setColor(Color.green);
		g2.fillPolygon(new int[]{50, 150, 100}, new int[]{50, 50, 100}, 3);
	}

	public void renderBoard(Board b) {
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				if (b.pieces[y][x] == -1) {
					g2.setColor(new Color(177, 226, 242, 190));
					g2.fillOval(x * slotDiam + (x + 1) * horizontalSlotSpacer, y * slotDiam + (y + 1) * verticalSlotSpacer, slotDiam, slotDiam);
				} else if (b.pieces[y][x] == 0) {
					g2.setColor(new Color(174, 179, 181, 200));
					g2.drawOval(x * slotDiam + (x + 1) * horizontalSlotSpacer, y * slotDiam + (y + 1) * verticalSlotSpacer, slotDiam, slotDiam);
				} else if (b.pieces[y][x] == 1) {
					g2.setColor(new Color(255, 0, 0, 150));
					g2.fillOval(x * slotDiam + (x + 1) * horizontalSlotSpacer, y * slotDiam + (y + 1) * verticalSlotSpacer, slotDiam, slotDiam);
				}
			}
		}
	}

	public void renderQuad(Quad q) {
		if (q != null) {
			g2.setColor(Color.yellow);
			g2.drawRect(q.x, q.y, q.width, q.height);
		}
	}
}