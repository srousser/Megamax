package board;

public class Quad {

	public int row, col;
	public int x, y;
	public int width, height;

	public Quad(int col, int row, int x, int y, int width, int height) {
		this.col = col;
		this.row = row;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean contains(int x, int y) {
		return (x >= this.x && x < this.x + width && y >= this.y && y < this.y + height);
	}

}